// dec 17 - 9:00pm ~ 9:15pm

/* analysis O(n^2) --> 2 nested loops
1. declare hashset: set<list<integer>> set & arrays.sort(nums) --> ex: -4 -1 -1 0 1 2
2. loop through up until nums.length - 2
    - if duplicate (i > 0 && nums[i] == nums[i-1]), continue.
    - 2 var: a & b.. a --> i + 1, b --> nums.length - 1. 
        - while (a < b) 2 pointer
            - if a+b+i == 0 AND set doesn't contain the combo, arrays.aslist(nums[a], b, i..)
                add them to the set as Arrays.asList();
            - else if value is too large, b--. else, a++.
3. return: new ArrayList(set);
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums); // -4 -1 -1 0 1 2
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int a = i + 1;
            int b = nums.length - 1;

            while (a < b) {
                if (nums[a] + nums[b] + nums[i] == 0 && !set.contains(Arrays.asList(nums[a], nums[b], nums[i]))) {
                    set.add(Arrays.asList(nums[a], nums[b], nums[i]));
                } else if ((nums[a] + nums[b] + nums[i]) > 0) {
                    // too large. b--;
                    b--;
                } else {
                    a++;
                }
            } 
        }
        return new ArrayList(set);
    }
}
