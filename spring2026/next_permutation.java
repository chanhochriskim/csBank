// feb 10 - day 3 at PMU. 11:10pm ~ 11:20pm
// technically i looked at the solution but... surprisingly it was pretty easy..?
/* solution 
1. have to find the first decreasing element from right.
2. find the smallest element from the right side, which's greater than first decreasing element.
3. swap these two.
4. then, reverse the sublist from the first decreasing element to end.
*/
class Solution {
    // 1 4 3 8 5. --> 1 4 5 8 3. --> 1 4 5 3 8.
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int j = nums.length - 1; 
        while (j >= i && nums[j] <= nums[i - 1]) {
            j--;
        }
        swap(nums, i - 1, j); // i = 8 --> i - 1 (3). j = 5.
        reverse(nums, i, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}


// feb 11 - day 4. 10:40pm ~ 10:55pm
// technically i looked at the solution but... surprisingly it was pretty easy..?
/* solution 
1. have to find the first decreasing element from right.
2. find the smallest element from the right side, which's greater than first decreasing element.
3. swap these two.
4. then, reverse the sublist from the first decreasing element to end.
*/
class Solution { 
    // 1 4 7 8 6. --> 1 4 8 7 6. -> 1 4 8 6 7.
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;

        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int j = nums.length - 1;
        while (j >= i && nums[j] <= nums[i - 1]) {
            j--; 
        }
        swap(nums, i - 1, j);
        reverse(nums, i, nums.length - 1);
    }

    private void swap(int[] nums, int start, int end) {
        int temp = nums[end];
        nums[end] = nums[start];
        nums[start] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        // 1 2 3 4 5 --> 5 4 3 2 1 
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
