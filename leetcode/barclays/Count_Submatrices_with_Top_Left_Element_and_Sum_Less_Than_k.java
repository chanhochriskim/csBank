// dec 7 -- speedrun 11:50pm ~ 12:00
/*
prefix sum

*/

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i > 0) { // the very edged ones are not cumulated
                    grid[i][j] += grid[i - 1][j];
                }
                if (j > 0) {
                    grid[i][j] += grid[i][j - 1];
                }
                if (i > 0 & j > 0) {
                    grid[i][j] -= grid[i-1][j-1]; // subtract the diagnoal one (duplicate value)
                }
                if (grid[i][j] <= k) { // if satisfies the counting, add it
                    res++;
                }
            }
        }

        return res;
    }
}
