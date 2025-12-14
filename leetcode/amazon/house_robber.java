// dec 14 -- 11:45am ~ 12:00pm 
/* DP
- dp[i] = maximum money that can be robbed from nums[0~i]
- 2 choices to make (either rob or skip the current house)
    - skip house i --> money stays same as dp[i-1]
    - rob house i --> must skip house 'i-1', moeny = nums[i] + house[i-2]

*/

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        // set the 0th and 1st one & loop from i = 2. 
        // dp: either get to 'skip' the house || 'rob' the house (curr + a house before sum)
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]); 

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]); 
        }        

        return dp[nums.length - 1];
    }
}
