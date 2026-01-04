// jan 3 (airport) -- 6:25pm ~ 6:45pm 
// backtracking: choose --> explore --> un-choose (backtrack) <-- forgot about this part!
class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] checker = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0, checker)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int count, boolean[][] checker) {
        if (word.length() == count) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(count)
                || checker[i][j]) {
            return false;
        }
        checker[i][j] = true; // mark

        boolean found = dfs(board, word, i + 1, j, count + 1, checker) ||
                dfs(board, word, i - 1, j, count + 1, checker) ||
                dfs(board, word, i, j + 1, count + 1, checker) ||
                dfs(board, word, i, j - 1, count + 1, checker);
        checker[i][j] = false;
        return found;
    }
}
