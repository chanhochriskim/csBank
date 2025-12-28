// dec 28 -- 10:35am ~ 10:55pm

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

        ListNode temp = head;
        ListNode res = head; // result to be returned. 
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }

        temp = head;
        for (int i = 0; i < (count - n); i++) {
            head = temp;
            temp = temp.next;
        }
        head.next = temp.next;

        return res;
    }
}
