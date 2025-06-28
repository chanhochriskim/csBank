// took 30:52. needed "if (n == length) return head.next; // in case n == length, get rid of head.next." line for passing every edge case.


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
        if (head == null || (n == 1 && head.next == null)) return null; 
        
        int length = 0; // gotta be at least one.
        ListNode count = head;
        ListNode res = head; // sainvg result.
        while (count != null) {
            count = count.next;
            length++;
        }
        if (n == length) return head.next; // in case n == length, get rid of head.next.
        // now, we get the proper length.

        ListNode temp = head;
        for (int i = 0; i < (length - n); i++) {
            head = temp;
            temp = temp.next;
        }
        // now, head is right behind temp.
        head.next = temp.next; // skipping the temp value.

        return res;
    }
}
