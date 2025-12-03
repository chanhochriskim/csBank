// dec 3 -- 10:50am ~ 11:05am
/* backtracking once again (i dont think im understanding backtrackign properly man whatever imma just rawdog it then)
--> backtrack = basically recursive dfs to go back when necessary and conduct process again.
*/

// ex: candidates: [2,3,6,7] // target = 7.
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList();
        backtrack(candidates, target, 0, new ArrayList(), ans);
        return ans;
    }

    public void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList(current));
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, ans);
            current.remove(current.size() - 1);
        }
    }
}
