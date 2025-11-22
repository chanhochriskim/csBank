// nov 22 - 1:15pm ~ 1:25pm
/* quick analysis (lowk forgot how to do it so had to lock in lmao)
- use: prev = null // next = head.next (temp value)
-> analogy:
next = head.next; // temp value save
head.next = prev; // change pointer
prev = head; // prev move
head = next; // head move over to the next temp value

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
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}
