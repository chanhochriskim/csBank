// jan 13 - 1:30pm ~ 1:45pm
// leetcode locked so had to do it on neetcode75
// well, it sure was easy but took a minute due to syntax LMAO

/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.size() == 0) return true;
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        Interval first = intervals.get(0); // (5,8)
        int prevEnd = first.end;

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i); // (9,15)
            if (prevEnd <= curr.start) {
                prevEnd = curr.end;
            } else {
                return false;
            }
        }
        return true;
    }
}
