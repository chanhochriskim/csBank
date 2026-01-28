// jan 27 - 1:25pm ~ 2:00pm lowkey took a hot minute to understand intuition behind smh 
/* 
When you’re at i, map[prefix-k] counts how many earlier places you could “start after” 
so that the subarray ending at i sums to k. 
In this case, the earlier place is index 1, so the subarray starts at 2, which is [3].
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        int prefix = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, 1); // initial starter for 0 value
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (map.containsKey(prefix - k)) {
                ans += map.get(prefix - k);
            }
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }

        return ans;
    }
}



// jan 28 - 10:30am ~ 10:40am
// review from yesterday 

class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int prefix = 0;
        HashMap<Integer, Integer> map = new HashMap();

        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (map.containsKey(prefix - k)) {
                ans += map.get(prefix - k); 
            }
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }

        return ans;
    }
}
