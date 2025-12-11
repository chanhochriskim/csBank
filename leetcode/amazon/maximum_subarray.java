// dec 11 - 9:55am ~ 10:15am
/* DP problem (kandane's algorithm --> greggHogg on youtube shorts)
- maxSum / curr_sum for O(n) solution.
- go over each loop. update maxSum. for math.max(maxsum, curr_sum)
- if curr_sum < 0, curr_sum = 0 (since negative value will take away the best value anyways)

*/

class Solution {
    public int maxSubArray(int[] nums) {
        int max_sum = Integer.MIN_VALUE;
        int curr_sum = 0;

        for (int num : nums) {
            curr_sum += num;
            max_sum = Math.max(max_sum, curr_sum); 
            if (curr_sum < 0) {
                curr_sum = 0;
            }
        }

        return max_sum; 
    }
}
