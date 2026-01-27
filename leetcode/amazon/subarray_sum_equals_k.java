// jan 27 - 1:25pm ~ 2:00pm lowkey took a hot minute to understand intuition behind smh 

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
