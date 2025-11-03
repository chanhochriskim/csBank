// nov 3 - 8:10am ~ 8:30am

/* analysis O(n^2)
- rol & col & sub 3*3 --> no duplicates
- unique value --> String HashSet in constant time
- String HashSet = only 1 hashset, checks found in row/col/sub-box
*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') { // if number, do the testing
                    if (!seen.add(board[i][j] + "row" + i) ||
                        !seen.add(board[i][j] + "col" + j) ||
                        !seen.add(board[i][j] + "sub-box" + i/3 + "-" + j/3)) {
                            return false;
                        }
                }
            }
        }

        return true;
    }
}
