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


// sep 24 -- 11:34pm 

/* similar approach as binary search move here and there
1. 2 pointers = low, high 
2. use them to get the mid.
3. if (nums[low] < nums[mid]) --> 4 ~ 7. means the part isn't rotated. 
3.5 check if target falls within. --> if so, high = mid - 1;
3.5 if not, low = mid + 1;
4. else, it's rotated. you still gotta go through 
4.5 if falls within, low = mid + 1;

*/

class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1 && nums[0] == target) return 0;
        int low = 0;
        int high = nums.length - 1; 

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } 
            
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1; // if falls within, shrink the size into that part
                } else {
                    low = mid + 1; // nope, we don't have target value here. move on.
                }
            } else {
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1; // if falls within, shrink into this part.
                } else {
                    // nope. move on.
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
