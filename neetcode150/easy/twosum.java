// oct 30 - 9:45am ~ 9:48am
// ah yes, the infamous two sum (my solution is n^2)
/* two pointer way i tried (imma j stick with hashmap lmao)
        // int left = 0;
        // int right = nums.length - 1;

        // while (left <= right) {
        //     if (nums[left] + nums[right] == target) {
        //         break;
        //     } else if (nums[left] + nums[right] > target) {
        //         right--;
        //     } else {
        //         left++;
        //     }
        // }

        // return new int[] {left, right};


*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[] {};
    }
}
