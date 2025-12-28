// dec 28 -- 9:50am ~ 10:20am

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
    public void reorderList(ListNode head) {
        // 1. stack them up
        Stack<ListNode> stack = new Stack();
        ListNode curr = head;
        while (curr != null) {
            stack.add(curr);
            curr = curr.next; 
        }
        curr = head;

        // 2. loop until half the stack size 
        ListNode nextFirst = curr.next;
        int len = stack.size() / 2;
        for (int i = 0; i < len; i++) {
            ListNode last = stack.pop();
            curr.next = last;
            last.next = nextFirst;
            curr = nextFirst;
            nextFirst = curr.next;
        }
        curr.next = null; // to terminate the list. 
    }
}
