// Aug 22 - late night leetcode sesh. O(nlogn) --> loop through n, but only half (logn)

/* thought process here:
1. first, sort them out intervals = [[1,3],[2,6],[8,10],[15,18]].
2. then, add the first trial (1,3) into the array so that we can loop through by comparing with it.
3. two things to consider --> last and curr. 
4. last is the last list within the current ans array.
5. curr is the one we are comparing with. 
6. compare curr's 0th index with last[1]. if 0th index falls within the last (smaller), then we update the last using Math.max
7. if not, treat it independently. just add it to the ans result.

*/
class Solution {
    public int[][] merge(int[][] intervals) {
        // sort them out & get ans
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = ans.get(ans.size() - 1); 
            int[] curr = intervals[i]; // 2 from [2,6]

            if (curr[0] <= last[1]) {
                last[1] = Math.max(last[1], curr[1]);
            } else {
                ans.add(curr);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
