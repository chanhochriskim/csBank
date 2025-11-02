// nov 2 -- 8:45am ~ 9:15am

/* approach: bucket sort O(n)
1. get the key / value (freq) map
2. bucket sort (based on the freq)
3. return the top k frequent ones by iterating backwards through the arary
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // frequency map
        Map<Integer, Integer> freqMap = new HashMap();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // bucket sort (which contains int list per bucket for multiple values)
        // nums.length + 1--> worst case, nums.length = 7 with [1,1,1..1]. freq = 7, so we need the 7th bucket (simply ignoring the index 0 one for simplicity)
        List<Integer>[] buckets = new List[nums.length + 1]; 
        freqMap.forEach((num, freq) -> {
            if (buckets[freq] == null) buckets[freq] = new ArrayList();
            buckets[freq].add(num);
        });

        // retrieve the top k most frequent elements accordingly
        List<Integer> ans = new ArrayList();

        for (int i = buckets.length - 1; i > 0 && ans.size() < k; i--) {
            if (buckets[i] != null) ans.addAll(buckets[i]);
        }
        
        int[] result = new int[ans.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}
