// sep 8 -- 1:50pm ~ 2:10pm.. was not setting subarray back to 0!

class Solution {
    public int subarraySum(int[] nums) {
        int ans = 0;

        int[] arr = new int[nums.length];
        arr[0] = nums[0]; 
        int start = 0;
        int subarray = 0;
        
        // fill up the subarrays [3,1,1,2]
        for (int i = 1; i < nums.length; i++) {
            start = Math.max(0, i - nums[i]); 
            for (int j = start; j <= i; j++) {
                subarray += nums[j]; 
            }
            arr[i] = subarray;
            subarray = 0; // reset its value
        }

        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
        }
        return ans;
    }
}
