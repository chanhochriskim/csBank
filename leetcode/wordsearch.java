// july 17 2025 -- ~35min (dfs part a lil chat helped)
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0)
            return false; // edge case
        char[] arr = word.toCharArray(); // characterized the string word.

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // if first letter matches, we check the rest. 
                if (dfs(board, i, j, arr, 0))
                    return true; // if checker triggered, true (found the match)
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, char[] arr, int index) {
        // dfs checker to see if it has one. 
        if (index == arr.length)
            return true;
        // if out of bound or mismatching --> false.
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (board[i][j] != arr[index])
            return false;
        char temp = board[i][j];
        board[i][j] = '$';
        boolean checker = dfs(board, i + 1, j, arr, index + 1) || dfs(board, i - 1, j, arr, index + 1)
                || dfs(board, i, j + 1, arr, index + 1) || dfs(board, i, j - 1, arr, index + 1);

        board[i][j] = temp; // restore the original value for the next round.
        return checker;
    }
}
