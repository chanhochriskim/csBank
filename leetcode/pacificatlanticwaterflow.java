// used chat for a bit but man it was fun. reveiewed

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length; 
        
        // tracking down which cells reach each ocean. (true / false)
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // top and bottom 2 rows - pacific top / atlantic bottom
        for (int i = 0; i < cols; i++) {
            dfs(0, i, pacific, heights[0][i], heights);
            dfs(rows - 1, i, atlantic, heights[rows - 1][i], heights);
        } 

        // left and right columns - pacific left / atlantic right 
        for (int j = 0; j < rows; j++) {
            dfs(j, 0, pacific, heights[j][0], heights);
            dfs(j, cols - 1, atlantic, heights[j][cols - 1], heights);
        }

        List<List<Integer>> res = new ArrayList();
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < cols; b++) {
                if (pacific[a][b] && atlantic[a][b]) {
                    res.add(Arrays.asList(a, b));
                }
            }
        }
        return res;
    }

    // skips 1. out of bound 2. already visited 3. current bigger than uphill.
    // * current must be smaller than uphill *
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
