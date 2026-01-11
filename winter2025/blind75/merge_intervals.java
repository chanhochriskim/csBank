// jan 11 - 10:55am ~ 11:05am (make sure to arrays.sort intervals before any)

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList();
        list.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i]; // [2,6]
            int[] past = list.get(list.size() - 1); // [1,3]

            if (curr[0] <= past[1]) { // merge-able  
                past[1] = Math.max(past[1], curr[1]);
            } else {
                list.add(curr);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
