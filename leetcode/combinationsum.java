class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList();
        if (candidates.length == 0)
            return ans; // edge case.

        for (int i = 0; i < candidates.length; i++) {
            List<Integer> temp = new ArrayList(); // temp holder. 
            int current = candidates[i];
            if (target == current) {
                ans.add(new ArrayList(current)); // if target == value, add it right away.
            }

            int a = target - current; // ex: 7 - 2 = 5. 
            if (a > current) {
                temp.add(current); // temp: [2]
            }

            while (a > current) { // loop until current value gets bigger than a.
                if (a > current) {
                    a -= current; // a = 5 - 2 = 3. 
                    temp.add(current); // [2,2]
                }
                for (int j = i; j < candidates.length; j++) {
                    if (a == candidates[j]) {
                        temp.add(candidates[j]); // [2,2,3]
                        ans.add(temp); // adding it to ans.
                        a = 0; // set a as 0.
                    }   
                }
            }

        }

        return ans;
    }
}
// <--- brute force approach which i was trying to get it done on my own.


// --> right way using DFS with backtracking.
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList();
        backtrack(candidates, target, 0, new ArrayList(), ans); 
        return ans;
    }

    public void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            // we found one.
            result.add(new ArrayList<>(current)); // add it to result.
            return; 
        }
        if (target < 0) {
            return; // wrong value.
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]); 
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1); // backtrck (get rid of the last one)
        }
    }
}
