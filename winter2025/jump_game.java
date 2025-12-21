// dec 21 - 10:25am ~ 10:35am
/*
starting at num.length - 2. goal = last idx. 
if nums[i] + i >= goal (can reach the goal? --> goal = i (update it) 
*/

class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= goal) {
                goal = i;
            }
        }


        return (goal == 0) ? true : false;
    }
}
