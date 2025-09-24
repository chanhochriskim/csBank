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

// sep 23 

/* analysis:
[3,4,5,1,2]
get low and high = 0, nums.length - 1; 
get mid. 
    1. if nums[mid] > nums[high] // we know the lowest must sit within the right
    2. --> low = mid + 1; & re-do it.
    3. else if (nums[low] < nums[high]) // high = mid. 
*/

class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low]; // return the lowest value.
    }
}
