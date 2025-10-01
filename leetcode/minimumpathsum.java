// oct 1 -- basic DP problem
/*
DP problem.
using up_path and left_path, calculate the minimum possible path. go along the way, retunr the last coordinate, which will be the answer.
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up_path = Integer.MAX_VALUE;
                int left_path = Integer.MAX_VALUE;

                if (i != 0) {
                    up_path = grid[i][j] + grid[i-1][j];
                }
                if (j != 0) {
                    left_path = grid[i][j] + grid[i][j-1];
                }
                if (i == 0 && j == 0) {
                    continue;
                } else {
                    grid[i][j] = Math.min(up_path, left_path);
                }
            }
        }



        return grid[m-1][n-1];
    }
}
