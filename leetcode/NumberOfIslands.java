// Jul 21 2025 -- ~28min.
// issue was, handling this part --> if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0').

// chris' gameplan. 
// 1. if grid[i][j] is triggered, call dfs. 
// 2. dfs works as setting every land in one island to be 0 (so next time it won't trigger)
// 3. once dfs is done, the count gets incremented.
// 4. repeat the process of setting lands to be 0. then, for every island, we increment count.

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null)
            return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    public void dfs(char[][] grid, int i, int j) {        
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // set it to be 0. 

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
