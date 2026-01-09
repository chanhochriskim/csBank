// jan 9 - 10:35am ~ 11:00am
/* (did it on my own after hint1 yay)
literally same approach as a classic house robber DP problem, 
except to rob either house[1] ~ house[n-1] || house[2] ~ house[n];

*/

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) {
            int temp = nums[2];
            temp = Math.max(temp, nums[1]);
            return Math.max(temp, nums[0]);
        }
        
        int[] dp = new int[nums.length - 1]; // house[1] ~ house[n-1]
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]); // 4
        }

        int[] dp2 = new int[nums.length - 1]; // house[2] ~ house[n]
        dp2[0] = nums[1];
        dp2[1] = Math.max(dp2[0], nums[2]); 

        for (int i = 2; i < nums.length - 1; i++) {
            dp2[i] = Math.max(dp2[i-1], nums[i+1] + dp2[i-2]);
        }
        // System.out.println("dp1 max: " +  dp[dp.length - 1] + " dp2 max: " + dp2[dp2.length - 1]);

        return Math.max(dp[dp.length - 1], dp2[dp2.length - 1]);
    }
}
