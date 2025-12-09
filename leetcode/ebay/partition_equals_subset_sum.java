// dec 8 -- 11:05pm ~ 11:22pm
/* DP problem (jinwoo guy solution --> he also has the example walkthrough very chad)
steps: 
- if sum of array is odd --> impossible. false. 
- targetSum = totalsum / 2 (cuz i one subset is added up to total, the rest will the other half)
- initialize boolean dp where dp[i] stores true/false (if having sum i by subset is possible or not) --> dp[0] = true (since 0 is obviosuly true)
- itearte over the array / keep updating dp[i] (DP knapsack logic)
- iterate from targetSum down to current number num to ensure each number is only used once. (reverse iteartion = prevents same number being used multiple times)
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 != 0) return false;

        int targetSum = totalSum / 2;

        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int currSum = targetSum; currSum >= num; currSum--) {
                dp[currSum] = dp[currSum] || dp[currSum - num];
                if (dp[targetSum]) return true;
            }
        }

        return dp[targetSum];
    }
}
