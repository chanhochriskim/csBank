// dec 27 -- 11:05pm ~ 11:10pm 

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
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            if (fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null) return false;
            if (fast == slow) return true;
        }

        return false;
    }
}
