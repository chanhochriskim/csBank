// dec19 -- 7:10pm ~ 7:20pm
/* TL DR walkthrough 
- sort the intervals array based on a[0] b[0]
- then add the first one to the ans list (list<int[]>)
- loop over from 1 ~ end 
    - compare curr[0] <= last[1] --> if so, update last[1] with math.max curr[1]
    - if not, just add the curr to list.
- return list --> list.toArray(new int[ans.size()][]);

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList();
        list.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] past = list.get(list.size() - 1);
            int[] curr = intervals[i];
            if (curr[0] <= past[1]) {
                past[1] = Math.max(curr[1], past[1]);
            } else {
                list.add(curr);
            }
        }


        return list.toArray(new int[list.size()][]);
    }
}
