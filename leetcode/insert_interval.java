// jan 10 -- 6:40pm ~ 7:40pm (not locked in, had to ask chat for debug)
/*
- arrays already sorted (yay)
- i think we compare start[1] with newinterval[0]. if overlap, thats when we do the merging job. 
*/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();

        int i = 0;
        int n = intervals.length;

        // 1) Add all intervals that end before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        // 2) Merge all overlapping intervals into newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);

        // 3) Add the rest
        while (i < n) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }
}
