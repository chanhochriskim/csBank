// nov 22 - 4:30pm ~ 4:40pm
/*
the intuition was correct, but just had to make sure to set 2 if statement boundaries within while (fast!=null) loop
    - at the beginning: fast.next != null --> false
    - before checking fast = slow, if fast == null --> false
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            if (fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) return false;
            if (slow == fast) return true;
        }

        return false;
    }
}
