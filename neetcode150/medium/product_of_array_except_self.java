// nov 2 - 9:30am ~ 9:50am
/* analysis --> boink? then boink back O(n)
- when boinking, set ans[0] as 1, then move along at 1. ans[i] = ans[i-1] * nums[i-1]
- then boink backward. set r = 1, ans[i] *= r. then update r with r *= nums[i]
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i-1] * nums[i-1];
        }

        int r = 1;
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] *= r;
            r *= nums[i];
        } 
        return ans;
    }
}
