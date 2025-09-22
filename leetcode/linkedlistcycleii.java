// using set. if set contains it, return that curr. however, this uses memory and we have to come up with O(1) memory.

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
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet();
        if (head == null) return null;
        ListNode curr = head;

        while (curr.next != null) {
            if (set.contains(curr)) return curr;
            set.add(curr);
            curr = curr.next;
        }

        return null;
    }
}
