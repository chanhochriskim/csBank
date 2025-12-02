// dec 2 -- 8:25am ~ 8:35am
/*
- add stones to pq. (maxheap)
- loop over pq.size, until pq.size == 1
- pop 2 things out. if value = same, continue (destory both). if not, y - x, then add back.
- return pq.peek();

*/

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : stones) {
            pq.add(i);
        }

        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();
            if (x == y) {
                continue;
            } else {
                pq.add(y - x);
            }
        }

        return (pq.size() == 1) ? pq.peek() : 0; // if value remains, pq.peek. if not, 0
    }
}
