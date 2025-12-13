/* dec 13 - 9:20am ~ 9:30am
1. set up the sanity checker of start & end is 0, and declare n value
2. set the 8 direction & queue (r, c, length) & visited boolean checker + initiate with starts
3. loop until queue gets empty. 
    - r, c, length value & if r c are end of matrix --> return length
    - 8 directional checker for-loop (int[] d : directions)
        - nr nc, if nr nc within the boundary & grid[nr][nc] = 0 & not visited
            - queue.offer (nr nc length+1)
            - visited = true!

*/

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n-1][n-1] != 0) return -1;

        int[][] directions = {
            {0,1}, {1, 1}, {-1, -1}, {1,0},
            {-1, 0}, {0, -1}, {-1, 1}, {1, -1}
        };

        Queue<int[]> queue = new LinkedList();
        boolean[][] visited = new boolean[n][n];
        queue.offer(new int[] {0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int length = curr[2];
            if (r == n - 1 && c == n - 1) return length;

            for (int[] d: directions) {
                int dr = r + d[0];
                int dc = c + d[1];
                if (dr >= 0 && dr < n && dc >= 0 && dc < n && grid[dr][dc] == 0 && !visited[dr][dc]) {
                    visited[dr][dc] = true;
                    queue.offer(new int[] {dr, dc, length+1});
                }
            }
        }       
        return -1;
    }
}




// dec 12 -- 9:25am ~ 10:10am
/* tried myself for 10 min, ended up watching neetcode solution
- 

*/

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        // 8 directions
        int[][] directions = {
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1},
            {0,1}, {1, 0}, {0, -1}, {-1, 0}
        };

        // queue stores: row, col, pathlength_so_Far
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[] {0, 0, 1}); // top-left, length 1
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll(); // first sequence, (0, 0, 1)
            int r = curr[0];
            int c = curr[1];
            int length = curr[2];

            // destination is reached:
            if (r == n - 1 && c == n - 1) return length;

            // neighbors checker
            for (int[] d: directions) {
                int nr = r + d[0];
                int nc = c + d[1];

                // boundary & obstacle & visted checker 
                if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc, length + 1});
                }
            }
        }
        return -1;
    }
}
