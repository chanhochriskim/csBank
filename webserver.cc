#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <ctype.h>
#include <errno.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <pthread.h>
#include <signal.h>
#include <dirent.h>
#include <time.h>
#include <limits.h> // for INT_MAX
#include <arpa/inet.h>

// for /stats & /logs part 
// --> it will help tracking request timing and URLs 
time_t serverStartTime;
int totalRequests = 0;

double minServiceTime = INT_MAX;
double maxServiceTime = 0;
char minURL[1024] = "";
char maxURL[1024] = "";
pthread_mutex_t statsMutex = PTHREAD_MUTEX_INITIALIZER;


#define QUEUE_LENGTH 5
#define MAX_NAME 1024

// struct which will hold name, mod time, file size, and is-directory flag
typedef struct {
    char name[256];
    char fullPath[512];
    off_t size;
    time_t modTime;
    int isDir;
} DirEntry;

void processHTTPRequest( int clientSocket ); // processHTTPRequest
void* handleClientThread(void* arg); // thread helper function
void* threadPool(void* arg); // pool thread helper function
void reapZombies(int signum); // zombie killer 
int serverSocket; // for file leak (robustness test)
void handleShutdown(int sig); // for file leak (robustness test)
int isDirectory(const char* path); // check if path is directory (Directory Browsing, Part 2)
const char* getIcon(const char* filename, int isDir); // icon helper (directory browsing)

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER; // mutex for thread safety
int main(int argc, char* argv[]) {
      // step 1: port number checker  & fork flag (-f part)
      int useFork = 0; // -f
      int useThread = 0; // -t
      int usePool = 0; // -p 
      int port = 0;

      if (argc < 2) {
          fprintf(stderr, "Usage: %s <port>\n", argv[0]);
          exit(EXIT_FAILURE);
      }

    if (argc == 3 && strcmp(argv[1], "-f") == 0) {
      useFork = 1; // fork flag
      port = atoi(argv[2]);
    } else if (argc == 3 && strcmp(argv[1], "-t") == 0) {
        useThread = 1;
        port = atoi(argv[2]);
    } else if (argc == 3 && strcmp(argv[1], "-p") == 0) {
        usePool = 1;
        port = atoi(argv[2]);
    } else if (argc == 2) {
        port = atoi(argv[1]);
    } else {
        fprintf(stderr, "Usage: %s [-f] <port>\n", argv[0]);
        exit(EXIT_FAILURE); 
    }

      // step 2: creating socket (pf_inet --> IPv4, sock_stream --> TCP)
      int serverSocket = socket(PF_INET, SOCK_STREAM, 0); // socket --> creates server's endpoint
      if (serverSocket < 0) {
          perror("socket");
          exit(EXIT_FAILURE);
      }

      // *we can use same port immedaitely* 
      // without this, we'll have to use different port everytime.
      // step 3: set socket options to reuse port (it says not required but helpful ig)
      int optval = 1;
      if (setsockopt(serverSocket, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval)) < 0) {
          perror("setsockopt");
          close(serverSocket);
          exit(EXIT_FAILURE);
      }

      // step 4: preparing the address structure 
      struct sockaddr_in serverAddress;
      memset(&serverAddress, 0, sizeof(serverAddress)); // clear the structure
      serverAddress.sin_family = AF_INET; // IPv4
      serverAddress.sin_addr.s_addr = INADDR_ANY; // accept connections from any ip (damn belongs to the street type shi)
      serverAddress.sin_port = htons(port); // convert port number to network byte order

      // step 5: bind the socket to address & port. 
      if (bind(serverSocket, (struct sockaddr*)&serverAddress, sizeof(serverAddress)) < 0) {
          perror("bind");
          close(serverSocket);
          exit(EXIT_FAILURE);
      }

      // step 6: start listening for incoming connections
      if (listen(serverSocket, QUEUE_LENGTH) < 0) { // listen puts the socket in passive mode, waiting for incoming connections
          perror("listen");
          close(serverSocket);
          exit(EXIT_FAILURE);
      }
      signal(SIGCHLD, reapZombies);
      signal(SIGPIPE, SIG_IGN); // prevents crashing on client disconnect
      signal(SIGINT, handleShutdown);
      serverStartTime = time(NULL); // record server start time
      printf("Server is listening on port %d\n", port);

     if (usePool) {
        pthread_t threads[5]; // thread pool size
        for (int i = 0; i < 5; i++) {
            if (pthread_create(&threads[i], NULL, threadPool, &serverSocket) != 0) {
                perror("pthread_create");
                close(serverSocket);
                exit(EXIT_FAILURE);
            }
            pthread_detach(threads[i]); // avoiding mem leaks.
        }

        while (1) {
            pause(); // waiting forever (main thread doesn't handle request.)
        }

        shutdown(serverSocket, SHUT_WR);
        close(serverSocket);
        return 0;
     } else {
      // step 7: loop forever, accept one request at a time.
      signal(SIGINT, handleShutdown);  // catch Ctrl+C
      while (1) {
        struct sockaddr_in clientAddress;
        socklen_t clientAddressLength = sizeof(clientAddress);
        int clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddress, &clientAddressLength);
        if (clientSocket < 0) {
            perror("accept");
            continue; // skip to the next iteration
        }
        printf("Accepted connection\n");
        // *****handling the request step.*****

        if (useFork) {
            pid_t pid = fork();
            if (pid < 0) {
                perror("fork");
                close(clientSocket);
                continue;
            } else if (pid == 0) {
                // child process
                processHTTPRequest(clientSocket);
                close(clientSocket);
                exit(0); 
            } else { // parent process
                close(clientSocket); // parent doesn't use the socket
            }
        } else if (useThread) {
            pthread_t threadId;
            int* clientSocketPtr = (int*)malloc(sizeof(int));
            if (clientSocketPtr == NULL) {
                perror("malloc");
                close(clientSocket);
                continue;
            }
            *clientSocketPtr = clientSocket; // pass the socket to the thread
            printf("[THREAD MODE] Creating new thread to handle clientSocket %d\n", *clientSocketPtr);
            if (pthread_create(&threadId, NULL, handleClientThread, clientSocketPtr) != 0) {
                perror("pthread_create");
                free(clientSocketPtr);
                close(clientSocket);
            } else {
                pthread_detach(threadId); // no need to join & detach the thread to avoid memory leaks
            }
        }
        else { // default iterative mode
            processHTTPRequest(clientSocket);
            close(clientSocket);
        }
        
      }

      shutdown(serverSocket, SHUT_WR);
      close(serverSocket);
      return 0;
    }
}

// icon helper (directory browsing)
const char* getIcon(const char* filename, int isDir) {
    if (isDir) return "/icons/menu.gif"; // directory icon
    const char* ext = strrchr(filename, '.');
    if (!ext) return "/icons/unknown.gif"; // unknown file type
    if (strcmp(ext, ".c") == 0 || strcmp(ext, ".cc") == 0) {
        return "/icons/index.gif";
    } 
    if (strcmp(ext, ".html") == 0 || strcmp(ext, ".txt") == 0) {
        return "/icons/text.gif";
    }
    if (strcmp(ext, ".jpg") == 0 || strcmp(ext, ".gif") == 0) {
        return "/icons/image.gif";
    }
    return "/icons/unknown.gif"; // default icondfd
}

// directory browsing part helper (name comparison)
int compareByName(const void* a, const void* b) {
    const DirEntry* fa = (const DirEntry*)a;
    const DirEntry* fb = (const DirEntry*)b;
    return strcmp(fa->name, fb->name);
}

// directory browsing part helper (size compariosin)
int compareBySize(const void* a, const void* b) {
    const DirEntry* fa = (const DirEntry*)a;
    const DirEntry* fb = (const DirEntry*)b;
    return (fa->size > fb->size) - (fa->size < fb->size); // like fa->size - fb->size but safer
}

// directory browsing part helper (date/time comparison)
int compareByTime(const void* a, const void* b) {
    const DirEntry* fa = (const DirEntry*)a;
    const DirEntry* fb = (const DirEntry*)b;
    return (fa->modTime > fb->modTime) - (fa->modTime < fb->modTime);
}

// file exist: 200 OK Header & actual file content. 404 if error.
void processHTTPRequest(int clientSocket) {
    int isPost = 0; // CGI-BIN extra credit, POST implementation 

    // read full HTTP request sent by browser or telnet.
    char buffer[1024];
    int bytesRead = read(clientSocket, buffer, sizeof(buffer) - 1); // read the request
    if (bytesRead < 0) {
        perror("read");
        close(clientSocket);
        return;
    }
    buffer[bytesRead] = '\0'; // null-terminate the string

    // this will search for a line like 'Content-Length: l1'
    int contentLength = 0;
    char* contentLengthHeader = strstr(buffer, "Content-Length: ");
    if (contentLengthHeader) {
        sscanf(contentLengthHeader, "Content-Length: %d", &contentLength);
    }

    char postData[1024] = ""; // buffer for POST data
    if (isPost && contentLength > 0) {
        int received = 0;
        while (received < contentLength) {
            int r = read(clientSocket, postData + received, contentLength - received);
            if (r <= 0) break;
            received += r;
        }
        postData[received] = '\0'; 
    }

    // checking for basic http authorization. (christiankim:googoogaagaa)
    const char* validAuth = "Authorization: Basic Y2hyaXN0aWFua2ltOmdvb2dvb2dhYWdhYQ==";
    if (strstr(buffer, validAuth) == NULL) {
        // if missing / invalid, 401 error.
        const char* unahtorizedResponse =
            "HTTP/1.1 401 Unauthorized\r\n"
            "WWW-Authenticate: Basic realm=\"myhttpd-cs252\"\r\n"
            "Content-Type: text/plain\r\n"
            "\r\n"
            "Access denied: valid credentials required.";
        write(clientSocket, unahtorizedResponse, strlen(unahtorizedResponse));
        close(clientSocket);
        return;
    }

    // parse the request (split the first line like GET /index.html HTTP/1.1)
    char method[16], path[1024], version[64]; // method: GET, path: /index.html, version: HTTP/1.1
    sscanf(buffer, "%s %s %s", method, path, version); // parse the request line
    isPost = strcmp(method, "POST") == 0;

    
    // log part of part 2
    FILE* logFile = fopen("log.txt", "a");
    if (logFile != NULL) {
        struct sockaddr_in addr;
        socklen_t addrSize = sizeof(addr);
        getpeername(clientSocket, (struct sockaddr*)&addr, &addrSize);
        char* clientIP = inet_ntoa(addr.sin_addr);
        fprintf(logFile, "Client: %s Path: %s\n", clientIP, path);
        fclose(logFile);
    }


    // stats part of part2
    clock_t start = clock();
    pthread_mutex_lock(&statsMutex);
    totalRequests++;
    pthread_mutex_unlock(&statsMutex); 

    // CGI-BIN FINE SHYT 
    fprintf(stderr, "[DEBUG] path = '%s'\n", path); // debug
    if (strncmp(path, "/cgi-bin/", 9) == 0) {
        fprintf(stderr, "[DEBUG] Entered CGI path block\n"); // debug statement
        // fork / parent / child stuff.
        pid_t pid = fork();
        if (pid == 0) { // child process
            // inside child 
            char* query = strchr(path, '?'); 
            if (query != NULL) {
                *query = '\0'; // split the path and query stringdfdf
                query++; // move past '?'
            } else {
                query = ""; 
            }

            setenv("REQUEST_METHOD", isPost ? "POST" : "GET", 1);

            if (!isPost) {
                setenv("QUERY_STRING", query, 1);
                setenv("CONTENT_LENGTH", "", 1);
                setenv("CONTENT_TYPE", "", 1);
            } else {
                // Set proper POST-related env vars
                char lenStr[16];
                snprintf(lenStr, sizeof(lenStr), "%d", contentLength);
                setenv("CONTENT_LENGTH", lenStr, 1);
                setenv("CONTENT_TYPE", "application/x-www-form-urlencoded", 1);
                setenv("QUERY_STRING", "", 1);  // POST does not use QUERY_STRING
            }

            setenv("SERVER_SOFTWARE", "CS252-lab5", 1);
            setenv("SERVER_PROTOCOL", "HTTP/1.1", 1);
            setenv("GATEWAY_INTERFACE", "CGI/1.1", 1);
            setenv("SERVER_PORT", "13330", 1);
            setenv("SCRIPT_NAME", path, 1);
            setenv("REMOTE_ADDR", "127.0.0.1", 1);
            setenv("SERVER_NAME", "data.cs.purdue.edu", 1);
            setenv("HTTP_ACCEPT", "*/*", 1);
            setenv("PATH_INFO", "", 1);
            setenv("PATH_TRANSLATED", "", 1);
            setenv("REMOTE_USER", "", 1);
            setenv("AUTH_TYPE", "", 1);


            dup2(clientSocket, STDOUT_FILENO); // redirect stdout to client socket
            dprintf(clientSocket,
                "HTTP/1.1 200 OK\r\n"
                "Content-Type: text/html\r\n"
                "Server: CS252-lab5\r\n"
                "\r\n");
                
          //  fflush(stdout);
            
            // execute the CGI script part
            char scriptPath[1024];
            snprintf(scriptPath, sizeof(scriptPath), "./http-root-dir/cgi-bin/%s", path + 9); // skip /cgi-bin/
            char* argv[] = {scriptPath, NULL}; // argv for execv

            if (isPost) {
                int pipefd[2];
                pipe(pipefd);
            
                // writing postData to pipe
                write(pipefd[1], postData, strlen(postData));
                close(pipefd[1]); // done writing
            
                // making child script read from pipe
                dup2(pipefd[0], STDIN_FILENO);
                close(pipefd[0]);
            }            

            fprintf(stderr, "[DEBUG] postData = '%s'\n", postData);
            fprintf(stderr, "[DEBUG] postData strlen = %ld\n", strlen(postData));
            execv(scriptPath, argv); // execute the script

            perror("execv"); // if execv fails, print error
            exit(1); 
        } else {
            waitpid(pid, NULL, 0);
            clock_t end = clock();
            double elapsed = (double)(end - start) / CLOCKS_PER_SEC;
            pthread_mutex_lock(&statsMutex);
            if (elapsed < minServiceTime) {
                minServiceTime = elapsed;
                strncpy(minURL, path, sizeof(minURL));
            }
            if (elapsed > maxServiceTime) {
                maxServiceTime = elapsed;
                strncpy(maxURL, path, sizeof(maxURL));
            }
            pthread_mutex_unlock(&statsMutex);
            close(clientSocket); // close the client socket in parent
            return; 
        }

    }

    // /stats request handler 
    if (strcmp(path, "/stats") == 0) {
        time_t now = time(NULL);
        int uptime = (int)difftime(now, serverStartTime);
        pthread_mutex_lock(&statsMutex);
        char statsPage[2048];
        snprintf(statsPage, sizeof(statsPage),
            "HTTP/1.1 200 OK\r\n"
            "Content-Type: text/html\r\n\r\n"
            "<html><body>"
            "<h1>Server Stats</h1>"
            "<p><b>Student:</b> Chris Kim</p>"
            "<p><b>Uptime:</b> %d seconds</p>"
            "<p><b>Total Requests:</b> %d</p>"
            "<p><b>Min Service Time:</b> %.6f sec (URL: %s)</p>"
            "<p><b>Max Service Time:</b> %.6f sec (URL: %s)</p>"
            "</body></html>",
            uptime, totalRequests,
            minServiceTime, minURL,
            maxServiceTime, maxURL
        );
        pthread_mutex_unlock(&statsMutex);

        write(clientSocket, statsPage, strlen(statsPage));
        close(clientSocket);
        return;
    }
    
    // logs request handler
    if (strcmp(path, "/logs") == 0) {
        FILE* logFile = fopen("log.txt", "r");
        if (logFile == NULL) {
            const char* response = 
            "HTTP/1.1 500 Internal Server Error\r\n"
            "Content-Type: text/plain\r\n\r\n"
            "Could not open log file.";
            write(clientSocket, response, strlen(response));
            close(clientSocket);
            return;
        }

        write(clientSocket, "HTTP/1.1 200 OK\r\n", strlen("HTTP/1.1 200 OK\r\n"));
        write(clientSocket, "Content-Type: text/plain\r\n\r\n", strlen("Content-Type: text/plain\r\n\r\n"));
        char line[1024];
        while (fgets(line, sizeof(line), logFile)) {
            write(clientSocket, line, strlen(line));
        }
        fclose(logFile);
        clock_t end = clock();
        double elapsed = (double)(end - start) / CLOCKS_PER_SEC;
        pthread_mutex_lock(&statsMutex);
        if (elapsed < minServiceTime) {
            minServiceTime = elapsed;
            strncpy(minURL, path, sizeof(minURL));
        }
        if (elapsed > maxServiceTime) {
            maxServiceTime = elapsed;
            strncpy(maxURL, path, sizeof(maxURL));
        }
        pthread_mutex_unlock(&statsMutex);
        return;
    }

    // (step 2 of browsing part) parse query string from path.
    char cleanPath[1024];
    char query[1024];
    char* qMark = strchr(path, '?'); // find the query string
    if (qMark) {
        strncpy(query, qMark + 1, sizeof(query));
        *qMark = '\0'; 
    }
    strcpy(cleanPath, path); // copy the path to cleanPath

    // (step 3 of browsing part)
    char sortBy[16] = "name"; // default sort by name
    char order[8] = "asc"; // default order is ascending
    if (qMark) {
        char* c = strstr(query, "C=");
        char* o = strstr(query, "O=");

        if (c && c[2]) {
            switch(c[2]) {
                case 'n': strcpy(sortBy, "name"); break;
                case 's': strcpy(sortBy, "size"); break;
                case 'd': strcpy(sortBy, "date"); break;
                default: break; // default is name
            }
        }
        if (o && o[2]) {
            switch(o[2]) {
                case 'D':
                    strcpy(order, "desc");
                    break;
                case 'A':
                    strcpy(order, "asc");
                    break;
            }
        }        
    }
    // robustness checker (prevent directory traversal
    if (strstr(path, "..") != NULL) {
    const char* forbiddenResponse =
        "HTTP/1.1 403 Forbidden\r\n"
        "Content-Type: text/plain\r\n\r\n"
        "Directory traversal not allowed.";
      write(clientSocket, forbiddenResponse, strlen(forbiddenResponse));
      printf("[SECURITY] Blocked traversal: %s\n", path);
      close(clientSocket);
      return;
    }

    // we allow both GET and POST. later, when setting CGI enviornment & reading body, we check isPost flag. 
    if (strcmp(method, "POST") == 0) {
        isPost = 1; 
    } else if (strcmp(method, "GET") != 0) {
        // only handle GET requests
        const char* response = "HTTP/1.1 501 Not Implemented\r\n\r\n"; // why 501, not 404..?
        write(clientSocket, response, strlen(response));
        close(clientSocket);
        return;
    }

    // building the full file path.
    char filePath[1024];
    if (strcmp(path, "/") == 0) {
        strcpy(filePath, "./http-root-dir/htdocs/index.html"); // default file
    } else {
        strncpy(filePath, "./http-root-dir/htdocs", sizeof(filePath) - 1);
        filePath[sizeof(filePath) - 1] = '\0'; // Just in case
        // Append the path safely
        strncat(filePath, path, sizeof(filePath) - strlen(filePath) - 1);
    }    

    // (ale sanudo) --> Redirect /dir to /dir/ if it's a directory but missing trailing slash
    if (isDirectory(filePath) && cleanPath[strlen(cleanPath) - 1] != '/') {
        char redirectLocation[1024];
        snprintf(redirectLocation, sizeof(redirectLocation),
            "HTTP/1.1 301 Moved Permanently\r\n"
            "Location: %s/\r\n"
            "Content-Type: text/html\r\n\r\n"
            "<html><body>Redirecting to <a href=\"%s/\">%s/</a></body></html>\r\n",
            cleanPath, cleanPath, cleanPath);
        write(clientSocket, redirectLocation, strlen(redirectLocation));
        close(clientSocket);
        return;
    }


    if (isDirectory(filePath)) {
        printf("[DIR] Detected directory: %s\n", filePath);
        DIR* dir = opendir(filePath);
        if (dir == NULL) {
            perror("opendir");
            const char* response = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
            write(clientSocket, response, strlen(response));
            close(clientSocket);
            return;
        }

        // start HTML repsonse
        write(clientSocket, "HTTP/1.1 200 OK\r\n", 17);
        write(clientSocket, "Content-Type: text/html\r\n\r\n", 28);
        write(clientSocket, "<html><body><h1>Chris Kim's Directory Listing</h1>", strlen("<html><body><h1>Chris Kim's Directory Listing</h1>"));
        char header[2048];
        snprintf(header, sizeof(header),
            "<table><tr>"
            "<th><a href=\"%s?C=n;O=%c\">Name</a></th>"
            "<th><a href=\"%s?C=d;O=%c\">Last modified</a></th>"
            "<th><a href=\"%s?C=s;O=%c\">Size</a></th>"
            "</tr>",
            cleanPath, (strcmp(sortBy, "name") == 0 && strcmp(order, "asc") == 0) ? 'D' : 'A',
            cleanPath, (strcmp(sortBy, "date") == 0 && strcmp(order, "asc") == 0) ? 'D' : 'A',
            cleanPath, (strcmp(sortBy, "size") == 0 && strcmp(order, "asc") == 0) ? 'D' : 'A'
        );
        write(clientSocket, header, strlen(header));

        // Compute parent directory safely
        char parentLine[1024];
        char parentLink[1024];

        strncpy(parentLink, cleanPath, sizeof(parentLink) - 1);
        parentLink[sizeof(parentLink) - 1] = '\0';

        // Remove trailing slash, but keep "/" root intact
        size_t len = strlen(parentLink);
        if (len > 1 && parentLink[len - 1] == '/') {
            parentLink[len - 1] = '\0';
        }

        // Now trim off the last segment
        char* lastSlash = strrchr(parentLink, '/');
        if (lastSlash && lastSlash != parentLink) {
            *lastSlash = '\0';
        } else if (lastSlash == parentLink) {
            // Edge case: path like "/testdir", want to keep just "/"
            parentLink[1] = '\0';
        } else {
            // We are already at root or something unexpected — reset to "/"
            strcpy(parentLink, "/");
        }


        snprintf(parentLine, sizeof(parentLine),
            "<tr>"
            "<td><img src=\"/icons/menu.gif\" alt=\"[DIR]\"> <a href=\"%s/\">Parent Directory</a></td>"
            "<td>-</td><td>-</td>"
            "</tr>",
            parentLink[0] ? parentLink : ""); // fallback to "/" if empty

        write(clientSocket, parentLine, strlen(parentLine));



        struct dirent* entry;
         // DirEntry struct for file info
        DirEntry entries[1024];
        int entryCount = 0;
        
        while ((entry = readdir(dir)) != NULL) {
            if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
                continue;
        
            char fullEntryPath[1024];
            snprintf(fullEntryPath, sizeof(fullEntryPath), "%.512s/%.255s", filePath, entry->d_name);
        
            struct stat entryStat;
            if (stat(fullEntryPath, &entryStat) != 0) continue;
        
            strncpy(entries[entryCount].name, entry->d_name, sizeof(entries[entryCount].name) - 1);
            entries[entryCount].name[sizeof(entries[entryCount].name) - 1] = '\0';  // null-terminate            
            entries[entryCount].size = entryStat.st_size;
            entries[entryCount].modTime = entryStat.st_mtime;
            entries[entryCount].isDir = S_ISDIR(entryStat.st_mode);
            entryCount++;
        }

        // sorting the entries based on the selected criteria
        if (strcmp(sortBy, "name") == 0) {
            qsort(entries, entryCount, sizeof(DirEntry), compareByName);
        } else if (strcmp(sortBy, "size") == 0) {
            qsort(entries, entryCount, sizeof(DirEntry), compareBySize);
        } else if (strcmp(sortBy, "date") == 0) {
            qsort(entries, entryCount, sizeof(DirEntry), compareByTime);
        }

        // reverse the order if descending
        if (strcmp(order, "desc") == 0) {
            for (int i = 0; i < entryCount / 2; i++) {
                DirEntry temp = entries[i];
                entries[i] = entries[entryCount - i - 1];
                entries[entryCount - i - 1] = temp;
            }
        }

        for (int i = 0; i < entryCount; i++) {
            const char* icon = getIcon(entries[i].name, entries[i].isDir);
            char line[2048];
            
            snprintf(line, sizeof(line),
                "<tr>"
                "<td><img src=\"%s\" alt=\"icon\"> <a href=\"%s%s%s\">%s</a></td>"
                "<td>%s</td>"
                "<td>%ld</td>"
                "</tr>",
                icon,
                cleanPath,
                (cleanPath[strlen(cleanPath) - 1] == '/') ? "" : "/", // conditional slash
                entries[i].name,
                entries[i].name,
                ctime(&entries[i].modTime),
                entries[i].size
            );

            write(clientSocket, line, strlen(line));
        }
        
        
        // close HTML
        write(clientSocket, "</ul></body></html>", strlen("</ul></body></html>"));
        close(clientSocket);
        closedir(dir);
        
        clock_t end = clock();
        double elapsed = (double)(end - start) / CLOCKS_PER_SEC;
        pthread_mutex_lock(&statsMutex);
        if (elapsed < minServiceTime) {
            minServiceTime = elapsed;
            strncpy(minURL, path, sizeof(minURL));
        }
        if (elapsed > maxServiceTime) {
            maxServiceTime = elapsed;
            strncpy(maxURL, path, sizeof(maxURL));
        }
        pthread_mutex_unlock(&statsMutex);
        return; // without this, it will continue to file-opening logic even after already sending the directory listing.
    }    
    
    // opening file.
    int fd = open(filePath, O_RDONLY);
    if (fd < 0) {
        // file not found, send 404 response
        const char* response = "HTTP/1.1 404 Not Found\r\n\r\n";
        write(clientSocket, response, strlen(response));
        close(clientSocket);
        clock_t end = clock();
        double elapsed = (double)(end - start) / CLOCKS_PER_SEC;
        pthread_mutex_lock(&statsMutex);
        if (elapsed < minServiceTime) {
            minServiceTime = elapsed;
            strncpy(minURL, path, sizeof(minURL));
        }
        if (elapsed > maxServiceTime) {
            maxServiceTime = elapsed;
            strncpy(maxURL, path, sizeof(maxURL));
        }
        pthread_mutex_unlock(&statsMutex);

        return;
    }

    // file found, send 200 OK response and file content.
    write(clientSocket, "HTTP/1.1 200 OK\r\n", strlen("HTTP/1.1 200 OK\r\n"));
    write(clientSocket, "Server: CS252-lab5\r\n", strlen("Server: CS252-lab5\r\n"));
    write(clientSocket, "Content-type: text/html\r\n\r\n", strlen("Content-type: text/html\r\n\r\n"));
        
    char fileBuffer[1024]; // send file contents 
    int n;
    while ((n = read(fd, fileBuffer, sizeof(fileBuffer))) > 0) {
        write(clientSocket, fileBuffer, n); // send file content
    }
    close(fd); // ensure i always close the file.

    // starting here -> *this block tracks how long each request takes & updates min/max service times and URLs*
    clock_t end = clock();
    double elapsed = (double)(end - start) / CLOCKS_PER_SEC;

    pthread_mutex_lock(&statsMutex);
    if (elapsed < minServiceTime) {
        minServiceTime = elapsed;
        strncpy(minURL, path, sizeof(minURL));
    }
    if (elapsed > maxServiceTime) {
        maxServiceTime = elapsed;
        strncpy(maxURL, path, sizeof(maxURL));
    }
    pthread_mutex_unlock(&statsMutex);
    // <- ends here
    close(clientSocket);
    close(fd);
}


// thread helper function
void* handleClientThread(void* arg) {
    int clientSocket = *(int*)arg;
    free(arg); // free the allocated memory for client socket
    processHTTPRequest(clientSocket);
    close(clientSocket);

    // debugging to prove that shit works
   // sleep(3);
    return NULL;
}

// pool thread helper function 
void* threadPool(void* arg) {
    int serverSocket = *(int*)arg;

    while (1) {
        struct sockaddr_in clientAddress;
        socklen_t clientAddressLength = sizeof(clientAddress);

        pthread_mutex_lock(&mutex); // lock the mutex (global pthread one)
        int clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddress, &clientAddressLength);
        pthread_mutex_unlock(&mutex); // unlock the mutex
        if (clientSocket < 0) {
            perror("accept");
            continue; // skip to the next iteration
        }
        printf("Accepted connection form POOL THREAD! \n");
        processHTTPRequest(clientSocket);
        close(clientSocket); 
    }

    return NULL;
}

void reapZombies(int signum) {
  while (waitpid(-1, NULL, WNOHANG) > 0);
}

void handleShutdown(int sig) { // debugging message included.
    printf("\n[SERVER] Caught signal %d, shutting down...\n", sig);
    shutdown(serverSocket, SHUT_WR);
    close(serverSocket);
    exit(0);
}

int isDirectory(const char* path) {
    struct stat pathStat;
    return (stat(path, &pathStat) == 0 && S_ISDIR(pathStat.st_mode));
}
