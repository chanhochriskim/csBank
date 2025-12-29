// dec 28 -- 11:00pm ~ 11:20pm
// --> old solution (using priorityqueue = nlogn. if uses divide & conquer --> would've been faster but im just too lazy rn)
// priorityqueue: sort all in asencding order. then, wire them in.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (ListNode head : lists) {
            while (head != null) {
                queue.add(head.val);
                head = head.next;
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            head.next = new ListNode(queue.remove());
            head = head.next;
        }
        
        return dummy.next;
    }
}
