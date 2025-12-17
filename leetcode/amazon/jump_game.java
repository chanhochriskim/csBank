// dec 16 - 6:45pm - 7:15pm
/* Greedy in O(n) time (neetcode)
- starting at the end, and revesre through it. shift the goal if current can make it.
- for example, starting at the 2nd largest i-th position. there, check if from that place, we can reach to the end. if so, update goal and so on.
*/

class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        // [2, 3, 1, 1, 4]
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= goal) {
                goal = i;
            }
        }

        return (goal == 0) ? true : false;
    }
}
