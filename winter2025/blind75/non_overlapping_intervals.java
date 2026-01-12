// jan 22 - 2:50pm ~ 3:20pm
/* -- neetcode O(nlogn)
- sort the arrays based on start. 
- 2 starting variables: res & prevEnd (first interval's end point)
- loop over i = 1 ~ n.
- if start >= prevEnd --> (not overlap. only update prevEnd to new end)
- else: (overlap) res++ & update prevEnd with Math.min
*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int res = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start >= prevEnd) {
                prevEnd = end;
            } else {
                res++;
                prevEnd = Math.min(end, prevEnd);
            }
        }

        return res;
    }
}
