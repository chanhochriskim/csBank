// dec 23 - 10:45am ~ 11:00am
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
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1]; // if 6, 7-->0,,,,6.
        map.forEach((num, freq) -> {
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList();
            }
            bucket[freq].add(num);
        });

        List<Integer> list = new ArrayList();
        for (int i = bucket.length - 1; i >= 0 && list.size() < k; i--) {
            if (bucket[i] != null) {
                list.addAll(bucket[i]);
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
