// nov 24 -- 9:15am ~ 9:35am

/* tortoise method: T.C O(n), S.C O(1) <-- using slow & fast pointers 
- both start at 0th on nums
- first do-while loop. --> meeting point inside the cycle,so not necessarily a duplicate
- then, using tortoise method, set fast back to head, loop both slow & fast at the same rate. this will lead us to the entrance, which will be the duplicate value, which is the cycle entrance.
*/

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        // cycle detection
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // set fast back to head, do the tortoise method. return fast.
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
