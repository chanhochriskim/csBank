// oct 22 -- visa prep 

class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int n = boxGrid.length; // rows
        int m = boxGrid[0].length; // cols

        char[][] ans = new char[m][n];
        // fill out everything with '.' (empty
        for (char[] row : ans) {
            Arrays.fill(row, '.');
        }

        for (int r = 0; r < n; r++) {
            int i = m - 1; // start from the right most (gravity similuator)
            for (int c = m - 1; c >= 0; c--) {
                if (boxGrid[r][c] == '#') { // if jewel, set jewel, decrement i. 
                    ans[i][n - r - 1] = '#';
                    i--;
                } else if (boxGrid[r][c] == '*') { // if wall, set wall, and update i
                    ans[c][n - r - 1] = '*';
                    i = c - 1;
                }
            }
        }

        return ans;
    }
}
