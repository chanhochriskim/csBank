// dec 3 -- 9:25am ~ 9:50am
/* r.c: O(n*2^n) --> 2^n for all subsets & n for the array (tbh not understood it 100%)
- DFS recursion tree (2 choices: include or not include)
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        List<Integer> subsets = new ArrayList();
        dfs(nums, 0, ans, subsets);
        return ans;
    }

    public void dfs(int[] nums, int i, List<List<Integer>> ans, List<Integer> subsets) {
        if (i >= nums.length) { // went over all the i-th values.
            ans.add(new ArrayList<>(subsets));
            return;
        }

        // include the current value. (left side on the tree)
        subsets.add(nums[i]);
        dfs(nums, i + 1, ans, subsets);

        // NOT inlcude it. (right side on the tree)
        subsets.remove(subsets.size() - 1);

        // skip
        dfs(nums, i + 1, ans, subsets);

    }
}
