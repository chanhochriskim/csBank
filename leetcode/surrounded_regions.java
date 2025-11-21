// nov 21 -- 4:10pm ~ 4:40pm. goofy ahh question unncessarily took long 

/*
- instead of flipping right away like regular DFS / BFS, mark the safe ones first (T, for example)
- After that, if O is detected, make it X. if not, T --> O (back to safe one)
*/

class Solution {
    public void solve(char[][] board) {
        if (board.length == 0)
            return;

        // mark safe 'O's from border. 
        int rows = board.length;
        int cols = board[0].length;

        // top & bottom rows
        for (int j = 0; j < cols; j++) {
            traversal(board, 0, j);
            traversal(board, rows - 1, j);
        }

        // left & right columns
        for (int i = 0; i < rows; i++) {
            traversal(board, i, 0);
            traversal(board, i, cols - 1);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'T')
                    board[i][j] = 'O';
            }
        }
    }

    // mark 'T' for the safe ones. 
    public void traversal(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] != 'O')
            return;

        grid[i][j] = 'T';

        traversal(grid, i + 1, j);
        traversal(grid, i - 1, j);
        traversal(grid, i, j + 1);
        traversal(grid, i, j - 1);
    }

}
