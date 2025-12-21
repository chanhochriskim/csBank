// dec 20 - 10:10pm ~ 10:25pm
/* prefix, 2 cases:
1. if count == k --> ans++;
2. if prefix - k exists within map, ans += map.get(that much)
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            count += nums[i]; // count --> prefix 
            if (count == k) ans++;
            if (map.containsKey(count - k)) {
                ans += map.get(count - k);
            }
            map.put(count, map.getOrDefault(count, 0) + 1);
        }

        return ans;
    }
}
