// dec 2 - 8:40am ~ 8:50am
/* looked over the October solution lol 
O(nlogn)-->heap:nlogn / k-th closest: klogn

- get the priorityqueue with comparator (a, b) -> a[0] - b[0]; // minheap based on distance (simplified dist: x^2 + y^2)
- extrat k-th cloest (minheap, get the poll by < k)
*/

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minheap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            int dist = (x * x) + (y * y);
            minheap.add(new int[] {dist, x, y});
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] curr = minheap.poll();
            result[i][0] = curr[1];
            result[i][1] = curr[2];
        }

        return result;
    }
}
