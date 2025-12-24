// dec 23 -- 6:50pm - 7:05pm (by myself LOL!)
/* tl dr;
- sort the nums. three cases: whether consecutive / duplicate / neither.
- use int a, b, ans, temp. 
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int ans = 1;
        int temp = 0;
        int a = 0;
        // 1 2 3 4 100 200.
        for (int b = 1; b < nums.length; b++) {
            if (nums[b] == nums[b - 1]) {
                temp++; 
            } else if (nums[b] == nums[b - 1] + 1) {
                ans = Math.max(ans, b - a + 1 - temp);
            } else {
                temp = 0;
                a = b;
            }
        }
        return ans;
    }
}
