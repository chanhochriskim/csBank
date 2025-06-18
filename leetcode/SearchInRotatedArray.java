// took 40min. gpt helped

class Solution {
    public int search(int[] nums, int target) {
        // edge case handler -->
        if (nums.length == 1 && nums[0] == target)
            return 0;
        // <-- edge case handler 

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // 7 (for --> example 1)
            if (nums[mid] == target)
                return mid;

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }

        return -1;
    }
}
