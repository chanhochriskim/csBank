// Deloitte Prep - Aug 20: 5:40 ~ 

class Solution {
    public int subarraySum(int[] nums, int k) {
        int prefixSum = 0;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap();

        // loop through nums. 
        for (int i = 0; i < nums.length; i++) {
            // everytime we update prefixSum. 
            prefixSum += nums[i];

            // case 1: if (prefixSum - k) exists within hashmap.
            // --> result += that result's frequency.
            if (map.containsKey(prefixSum - k)) {
                result += map.get(prefixSum - k); 
            } 
            
            if (prefixSum == k){ // case 2: if it matches, increment result.
                result++;
            } 

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1); // update the hashmap.

        }

        return result;
    }
}
