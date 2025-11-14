// nov 14 -- 12:30am ~ 12:40am

class Solution {
    public int search(int[] nums, int target) {
        int a = 0;
        int b = nums.length - 1;
        if (nums.length == 1 && nums[0] == target) {
            return 0;
        }
        while (a <= b) { 
            int mid = a + (b - a) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] < target) {
                    a = mid + 1;
                } else {
                    b = mid - 1;
                }
            }
        }
        return -1;
    }
}
