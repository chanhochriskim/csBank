// feb 12 - day 5 2:10pm ~ 2:25pm

class Solution {
    public int removeDuplicates(int[] nums) {
        int a = 1; // updater 
        int curr = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != curr) {
                nums[a] = nums[i];
                curr = nums[i];
                a++;
            }
        }

        return a;
    }
}
