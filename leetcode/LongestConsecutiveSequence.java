class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        Arrays.sort(nums);
        int count = 0;
        int resets = 0;
        int i = 0;

        while (i + 1 < nums.length) {    
            if (nums[0] == nums[nums.length - 1]) return 1; 
            if ((nums[i] + 1) == nums[i+1]) { // normal case: count max.
                resets++;
                count = Math.max(count, resets);
                i++;
            } else if (nums[i] == nums[i+1]) { // duplicate handler
                i++;
            } else { // no consecutive or duplicates --> resets resets. i value move on.
                resets = 0;
                i++;
            }
        }
        count++;
        return count;
    }
}
