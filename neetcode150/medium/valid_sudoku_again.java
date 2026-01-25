// jan 24 - 10:20pm ~ 10:40pm

/* analysis O(n^2)
- rol & col & sub 3*3 --> no duplicates
- unique value --> String HashSet in constant time
- String HashSet = only 1 hashset, checks found in row/col/sub-box
*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet(); // treat "row" "col" "3x3"
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    if (!seen.add(board[i][j] + "row" + i) || !seen.add(board[i][j] + "col" + j) || !seen.add(board[i][j] + "3x3" + i/3 + "-" + j/3)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
