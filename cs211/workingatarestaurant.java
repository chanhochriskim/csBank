package topic1;// topic 1, 1st problem
import java.util.*;
import java.io.*;

public class restaurant {

    public static List<String> working(int n, String[] rows) {

        List<String> ans = new ArrayList<>();
        int p1 = 0; // pile 1 --> think of it as a flusher
        int p2 = 0; // pile 2 --> think of it as temp buffer
        int remainder = 0; // used for TAKE
        int decrement = 0; // used for TAKE

        for (int i = 0; i < rows.length; i++) {
            String[] split = rows[i].split(" "); // [DROP] [100]
            String cmd = split[0]; // DROP or TAKE
            int plates = Integer.parseInt(split[1]); // plates value

            if (cmd.equals("DROP")) {
                ans.add("DROP 2 " + plates);
                p2 += plates; // stack up plates in there.
            } else if (cmd.equals("TAKE")) {
                remainder = plates; // 50

                while (remainder > 0) {
                    if (p1 == 0) {
                        ans.add("MOVE 2->1 " + p2);
                        p1 = p2; // move everything to p1.
                        p2 = 0; // p2 is now 0.
                    }

                    // OG version of mine.
                    if (p1 >= remainder) { // 100 >= 50 checked --> pile 1 has enough to fulfill the rest
                        ans.add("TAKE 1 " + remainder); // take 1 50
                        p1 -= remainder; // 100-50 = 50
                        remainder = 0;   // done (set it to 0)
                    } else {
                        // pile 1 has fewer than we need, take all of it then loop again. ex: drop 20 take 10 drop 8 take 15
                        ans.add("TAKE 1 " + p1);
                        remainder -= p1;
                        p1 = 0;
                    }
                }

            }
        }

        return ans;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean trigger = true;
        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);
            if (n == 0) break; // end of line

            if (!trigger) {
                System.out.println();
            }
            trigger = false;

            String[] rows = new String[n]; // # of sequence (rows)

            for (int i = 0; i < n; i++) {
                rows[i] = br.readLine(); // saving each row info into string[] rows.
            }

            List<String> work = working(n, rows);

            for (String row : work) System.out.println(row); // print them out.
        }

    }
}
