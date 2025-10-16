// oct 16 -- salesforce OA
/*
TC: loop over nums, queries each once O(n + q)
SC: O(n) for minDist.

*/
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int[] minDist = new int[n];
        Arrays.fill(minDist, n); // MAX_VALUE -- ex: [7,7,7,7,7,7,7]
        HashMap<Integer, Integer> lastSeen = new HashMap();

        // modulo loop for circular array
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n; // get the idx after modulo 
            int v = nums[idx]; // then proper value.
            if (lastSeen.containsKey(v)) {
                int prev = lastSeen.get(v);
                int prevIdx = prev % n; // cuz prev may be larger number due to 2n.
                int d = i - prevIdx; // since we are moving forward, get the distance.
                // now, update idx, and prevIdx's distance
                minDist[idx] = Math.min(minDist[idx], d);
                minDist[prevIdx] = Math.min(minDist[prevIdx], d);
            }
            lastSeen.put(v, i); // add new, or replace if exists with the current
        }

        // now, using minDist, get the proper values
        List<Integer> ans = new ArrayList<>(queries.length);
        for (int q: queries) {
            int d = minDist[q]; // get the distance
            if (d >= n) {
                ans.add(-1);
            } else {
                ans.add(d);
            }
        }

        return ans;
    }
}
