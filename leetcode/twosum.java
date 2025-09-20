// sep 20 (very basic). tried to use 2 pointers, but just used Map --> still  O(n)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = nums.length - 1; i >= 0; i--) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i); // 2, 0 --> 7, 1, etc.
        }
        return new int[] {};
    }
}
