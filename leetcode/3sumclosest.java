// sep 20 --> starting at i, use the 2 sum method for the rest. if gap too big, right--. if gap too small, left++.

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                int gap = (nums[start] + nums[end] + nums[i]) - target; // 2 - 1 = 1.
                if (Math.abs(gap) < Math.abs(ans)) {
                    ans = gap; // smaller value --> update it.
                } else if (gap < 0) {
                    // if gap's smaller than target, increment left
                    start++;
                } else if (gap > 0) {
                    end--;
                } else {
                    // if output matches, just return target.
                    return target; 
                }
            }
        }   
        return ans + target;
    }
}
