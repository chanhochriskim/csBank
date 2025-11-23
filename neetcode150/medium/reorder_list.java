// nov 23 -- 9:00am ~ 9:20am
/* O(n) 
- pile up all the nodes in stack.
- set curr Node back to head (since we're reusing it)
- for loop until stack.size / 2 (only need half)
    - 2 nodes: next (curr.next) --> temp, last (stack.pop)
    - curr.next = last
    - last.next = next
    - curr = next
- curr.next = null // but why tho

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
    public void reorderList(ListNode head) {
        if (head == null) return;

        Stack<ListNode> stack = new Stack();
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            stack.add(curr);
            curr = curr.next;
            count++;
        }
        curr = head; 
        for (int i = 0; i < count / 2; i++) {
            ListNode temp = curr.next;
            ListNode last = stack.pop();
            curr.next = last;
            last.next = temp;
            curr = temp;
        }
        curr.next = null;
    }
}
