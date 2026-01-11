// jan 11 - 10:15am ~ 10:30am
/*
- add all intervals that end before newInterval starts. 
- merge all overlapping intervals into newInterval, then after loop, add the newinterval into the list. (ans) 
- add the rest intervals & return 'list.toArray(new int[list.size()][])'

*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        int n = intervals.length;

        List<int[]> list = new ArrayList();
        
        while (i < n && intervals[i][1] < newInterval[0]) {
          //  System.out.println("first triggered");
            list.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
          //  System.out.println("second triggered");
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(newInterval);

        while (i < n) {
            list.add(intervals[i]);
            i++;
        }
        
        return list.toArray(new int[list.size()][]);
    }
}
