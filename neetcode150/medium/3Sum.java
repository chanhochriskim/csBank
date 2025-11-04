// nov 4 - 8:30am ~ 8:40am 

/* analysis
- sort, start from beginning, loop until -2.
- if duplicate, then skip 'continue', skip the process.
- apply two sum method per trial

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums); // -4 -1 -1 0 1 2 

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue; // duplicate, skip.

            int a = i + 1; // one after i
            int b = nums.length - 1; // dead-end
            while (a < b) { 
                if ((nums[a] + nums[b] + nums[i] == 0) && !set.contains(Arrays.asList(nums[a], nums[b], nums[i]))) { // if equals to 0, set doesn't contain it, then add.
                    set.add(Arrays.asList(nums[a], nums[b], nums[i]));
                } else if (nums[a] + nums[b] + nums[i] < 0) {
                    a++; // if sum's shorthanded, then increment a.
                } else {
                    b--; // else, decrement b.
                }
            }
        }
        return new ArrayList(set);
    }
}
