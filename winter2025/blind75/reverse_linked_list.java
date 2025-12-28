// dec 27 -- 10:50pm ~ 10:55pm (listnode curr = null, not new listnode(0))

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
    public ListNode reverseList(ListNode head) {
        ListNode curr = null;

        while (head != null) {
            ListNode t = head.next;
            head.next = curr;
            curr = head;
            head = t;
        }

        return curr;       
    }
}
