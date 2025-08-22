// Deloitte Prep: Aug 22 -- 3:55 ~ 4:40

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // sort by end coordniates.
        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));

        int pop = 1; // minimum 1 arrow.
        int position = points[0][1]; // start shooting at the end of first balloon.

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > position) { // out of range. (ex: 7 > 6) so we need new pop arrow, and we update the position.
                pop++;
                position = points[i][1]; // now, position is 12 this time. (7, 12)
            }
        }

        return pop;
    }
}
