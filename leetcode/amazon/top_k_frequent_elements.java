// dec 16 - 8:55pm ~ 9:10pm
/* 
1. freqMap (num, freqValue)
2. bucketSort based on frequency (start at 1 for convenience)
            map.forEach((num, freq) -> { 
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList();
            }
            bucket[freq].add(num); // 3 -- 1, 2 -- 2, 1 -- 3
        });
3. in reverse, get the answers properly.
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        freqMap.forEach((num, freq) -> { // [1:3 / 2:2 / 3:1]
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList();
            }
            bucket[freq].add(num);
        });

        List<Integer> ans = new ArrayList();
        for (int i = bucket.length - 1; i > 0 && ans.size() < k; i--) {
            if (bucket[i] != null) {
                ans.addAll(bucket[i]);
            }
        }
        
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
