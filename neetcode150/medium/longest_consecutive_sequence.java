// nov 3 -- 8:50am ~ 9:15am

/* analysis
- 3 cases: either consecutive, duplicate, or neither
- ans, temp (for each sequence), i (pointer)

*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int ans = 0;
        int temp = 0;
        int i = 0;
        while (i + 1 < nums.length) {
            if (nums[i] + 1 == nums[i+1]) { // if consecutive, temp++, update ans
                temp++; 
                ans = Math.max(ans, temp);
            } else if (nums[i] == nums[i+1]) { // if duplicate, don't do any

            } else { // neither (...means not consecutive. reset temp)
                temp = 0;
            }
            i++;
        }

        return ans + 1; // ans + 1, since temp counts the 'link' between each number, not the count itself
    }
}
