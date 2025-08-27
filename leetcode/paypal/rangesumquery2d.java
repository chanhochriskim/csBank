// aug 26. ok the problem was the dup part. i was doing dp[row1 + 1][col1 + 1], instead of just row1 col1. 


class NumMatrix {
    int[][] dp; // create this prefix sum 2d array. (dp programming method so named it dp)

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        dp = new int[rows + 1][cols + 1]; // 5x5 --> 6x6 this time. 

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                // algo: add all incremented rows & sums. then subtract the duplicated one.
                // lastly, add the current matrix value.
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
       // System.out.println(Arrays.deepToString(dp)); --> setup confirmed correct.

    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int big = dp[row2 + 1][col2 + 1]; 
        int purple = dp[row1][col2+1] + dp[row2+1][col1];

        int dup = dp[row1][col1];

        return big - purple + dup;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
