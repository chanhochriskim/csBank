// dec 27 -- 6:25pm ~ 6:50pm (ok really was trying to solve it myself but had to look over the solution. retry later tho)

class Solution {
    public int search(int[] nums, int target) {
        int a = 0; 
        int b = nums.length - 1;

        while (a <= b) {
            int mid = a + ((b - a) / 2);
            if (nums[mid] == target) return mid;
            if (nums[a] <= nums[mid]) {
                if (nums[a] <= target && target <= nums[mid]) {
                    b = mid - 1;
                } else {
                    a = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[b]) {
                    a = mid + 1;
                } else {
                    b = mid - 1;
                }
            }
        }

        return -1;
    }
}
