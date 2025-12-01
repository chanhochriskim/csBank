// nov 30 -- 11:40pm ~ 11:55pm 
/*
- global values: priorityqueue (al) / k
- define the k, pq, and add values into pq.
- when add, return pq.peak after remove the top (to get the kth largest)
*/

class KthLargest {
    PriorityQueue<Integer> pq;
    int k = 0;

    public KthLargest(int k, int[] nums) {
        this.k = k; // global k
        pq = new PriorityQueue();
        for (int i : nums) {
            pq.add(i);
        }
    }
    
    public int add(int val) {
        pq.add(val);
        while (pq.size() > k) { // K-th largest [4, 5, 6, 1, 2, 3], k = 2. 6 > 2 
            pq.remove(); // in queue, we remove ethe smallest (so 1)
        }
        return pq.peek(); // returns the left most one, so in example, 5
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
