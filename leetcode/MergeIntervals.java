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


// sep 4 -- salesforce prep 

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList();
        ans.add(intervals[0]); // [1,3]

        for (int i = 1; i < intervals.length; i++) {
            // curr & last 
            int[] last = ans.get(ans.size() - 1); // get the last avaiable one  
            int[] curr = intervals[i]; // [2,6]

            if (last[1] >= curr[0]) {
                // tail is eqal or greater than head of curr. merge it.
                last[1] = Math.max(last[1], curr[1]);
            } else {
                // independent. add to ans.
                ans.add(intervals[i]);
            }
        }

        return ans.toArray(new int[ans.size()][]);

    }
}

// nov 19 -- 9:25am ~ 9:40am

/* TL DR
- sort the intervals array based on a[0] b[0]
- then add the first one to the ans list (list<int[]>)
- loop over from 1 ~ end 
    - compare curr[0] <= last[1] --> if so, update last[1] with math.max curr[1]
    - if not, just add the curr to list.
- return list --> list.toArray(new int[ans.size()][]);

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // 1
        List<int[]> list = new ArrayList();
        list.add(intervals[0]); // 2

        for (int i = 1; i < intervals.length; i++) {
            int[] last = list.get(list.size() - 1);
            int[] curr = intervals[i]; // 2 from [2,6]
            if (curr[0] <= last[1]) { // comparing 2 from [2,6] and 3 from [1,3]
                last[1] = Math.max(last[1], curr[1]); // update the last with curr's last
            } else {
                list.add(curr);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
