// jan 16 -- 8:07pm ~ 8:20pm
// not part of blind 75 but whatever LMAO

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count = Math.max(count, dfs(grid, i, j));
                }
            }
        }

        return count;
    }

    public int dfs(int[][] grid, int i, int j) {
        int curr = 1;
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 0; 
    //    curr++;

        curr += dfs(grid, i - 1, j);
        curr += dfs(grid, i + 1, j);
        curr += dfs(grid, i, j + 1);
        curr += dfs(grid, i, j - 1);

        return curr;
    }
}
