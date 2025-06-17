class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) { // mid = shorter than low
                low = mid + 1;
            } else {
                // mid --> taller than low.
                high = mid;
            }
        }
        return nums[low];
    }
}
