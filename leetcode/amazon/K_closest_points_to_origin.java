// oct 7
/* analysis 
t.c (total: O(nlog))
inserting all n points into heap: O(nlogn)
removing k closest points: O(klogn)

s.c (overall: O(n))
priorityqueue stored all n points: O(n)
result array of size k: O(k)

1. get minheap, and append [dist,x,y] pair to minheap. *dist = x^2 + y^2 for simplicity since we are not getting actual distance.
2. then, heapq.heapify(minheap) <-- klogn 
3. declare int[][] result
4. while loop until k gets to 0 (getting the shortest k values within heap)
    5. get the pair [dist,x,y] from heapq.heapop(minheap)
    6. result.append([x,y])
7. then, simply return result.

*/
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // 1. minheap sorted by distance from (0,0) <-- smallest comes first.
        // compare based on dist (0th index)
        PriorityQueue<int[]> minheap = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        // insert all points w/ squared distance (no need to do full calcluation)
        for (int[] p: points) {
            int x = p[0];
            int y = p[1];
            int dist = (x*x) + (y*y);
            minheap.offer(new int[] {dist, x, y}); // [dist,x,y] format
        }

        // extract k closest points
        int[][] result = new int[k][2]; // by k many subarrays, each has 2 values(x,y)
        for (int i = 0; i < k; i++) {
            int[] curr = minheap.poll(); // [dist,x,y]
            result[i][0] = curr[1]; // i's 0th index: curr's 1st index (which is x)
            result[i][1] = curr[2]; // i's 1st index: curr's 2nd index (which is y)
        }

        return result;
    }
}
