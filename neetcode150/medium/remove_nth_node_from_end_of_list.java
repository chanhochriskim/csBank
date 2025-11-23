// nov 23 -- 10:00am ~ 10:20am
/*
- use 2 listnode pointers (slow, fast)
- move fast upto nth node (3th idx for sample 1 problem)
- then, use while loop to move both fast and slow and the same rate. by the time loop is gone, slow will be exactly 1 node before the 'needs to be deleted' node.
- simply do slow.next = slow.next.next to bypass the deleting one.
- return dummy.next

*/

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }       

        while (fast != null) {e
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
