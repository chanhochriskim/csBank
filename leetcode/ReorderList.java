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
        /* Chris' gameplan: (for example 1)
        1. add everything on stack 
        2. currently, head is (1) 
        3. from there, head.next = stack.pop (4)
        4. then, head.next = temp (2)
        5. then, temp.next = stack.pop (repeat 3~4) 
        until count = stack.max count (meaning we've got every node.)
        */
        if (head == null || head.next == null) return;

        Stack<ListNode> stack = new Stack();
        ListNode curr = head; 
        int stackMaxCount = 0;

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
            stackMaxCount++;
        }

        curr = head;
        for (int i = 0; i < (stackMaxCount / 2); i++) {
            ListNode next = curr.next; // 2
            ListNode last = stack.pop(); // 4
            curr.next = last; // 1 -> 4
            last.next = next; // 1 -> 4 -> 2
            curr = next;
        }
        curr.next = null;
    }
}
