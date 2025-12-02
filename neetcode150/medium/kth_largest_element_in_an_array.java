// dec 2 -- 9:00am ~ 9:05am
/*
- i mean, using priorityqueue was super easy but i guess we can't sort it? O(nlogk)
*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n : nums) {
            pq.add(n);
        }
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        return pq.peek();
    }
}
