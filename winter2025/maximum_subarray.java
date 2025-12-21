// dec 20 - 9:50pm ~ 10:00pm 

// -2 1 -3 4 -1 2 1 -5 4
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curr = 0;

        for (int num : nums) {
            curr += num; // 3
            max = Math.max(max, curr); // -2, 1, 4, 5, 6
            if (curr < 0) {
                curr = 0;
            }
        }
        return max;
    }
}
