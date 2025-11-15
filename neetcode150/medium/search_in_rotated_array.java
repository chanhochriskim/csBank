// nov 15 - 2:30pm ~ 2:45pm
/* analysis:
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
        int a = 0;
        int b = nums.length - 1;

        while (a <= b) { // 4 5 6 7 0 1 2
            int mid = a + (b - a) / 2;
            if (nums[mid] == target) return mid;
            if (nums[a] <= nums[mid]) { // 4 < 7 (check)
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
