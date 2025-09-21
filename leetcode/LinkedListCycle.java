// 35:41. i did slow.val == fast.val, but instead i had to do slow = fast.
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

// sep 21 -- 9:40am (before church) ~ 10:00am. 
// you CAN NOT do slow.val == fast.val cuz of duplicates. should've done slow == fast.

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
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) { // if null we know there isn't a cycle --> false.
            if (fast.next == null) return false; 
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) return false; 
            if (slow == fast) return true;
        }

        return false; // so just return false 
    }
}
