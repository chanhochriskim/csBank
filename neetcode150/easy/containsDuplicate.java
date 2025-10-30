// oct 30 - neetcode 150 9:20am ~ 9:23am mad easy 

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }

        return false;
    }
}
