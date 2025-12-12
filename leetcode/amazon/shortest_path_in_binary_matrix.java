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
