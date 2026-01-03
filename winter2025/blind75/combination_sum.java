// jan 3 (airport) -- 5:25pm ~ 5:50pm
/*
backtracking: 
- use backtrack helper to backtrack through the options via for-loop. using current arraylist, keep adding stuff, and if target = 0 triggered, then add that to ans arraylist. if not, (target < 0) return. 
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList();
        backtrack(candidates, target, 0, new ArrayList(), ans); // 3rd = start. 4th = current.
        return ans;
    }

    public void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return; // good. add the current value, then immediately move on. 
        } else if (target < 0) {
            return; // bad value. move to the next iteration.
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]); 
            backtrack(candidates, target - candidates[i], i, current, ans);
            current.remove(current.size() - 1);
        } 
    }
}
