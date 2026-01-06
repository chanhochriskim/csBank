// jan 6 -- 11:55am ~ 12:15pm 
/*
- tracking down which cells reach each ocean using 2d boolean array, pacific & atlantic
    - 2 loops (top & bottom 2 rows / left & right 2 columns)
- after dfs on both, only add the ones that has 'true' values on both pacific & atlantic
*/

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length; 

        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        // top & bottom 2 rows (pacific top & atlantic bottom)
        for (int i = 0; i < col; i++) {
            dfs(0, i, pacific, heights[0][i], heights);
            dfs(row - 1, i, atlantic, heights[row-1][i], heights);
        }

        // left & rigth 2 columns 
        for (int i = 0; i < row; i++) {
            dfs(i, 0, pacific, heights[i][0], heights);
            dfs(i, col - 1, atlantic, heights[i][col - 1], heights);
        }

        List<List<Integer>> list = new ArrayList();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    list.add(Arrays.asList(i, j));
                }
            }
        }
        return list;
    }

    public void dfs(int row, int col, boolean[][] visited, int prevHeight, int[][] heights) {
        if (row < 0 || row >= heights.length || col < 0 || col >= heights[0].length || visited[row][col] || heights[row][col] < prevHeight) {
            return;
        }

        visited[row][col] = true;
        dfs(row + 1, col, visited, heights[row][col], heights);
        dfs(row - 1, col, visited, heights[row][col], heights);
        dfs(row, col + 1, visited, heights[row][col], heights);
        dfs(row, col - 1, visited, heights[row][col], heights);

    }
}
