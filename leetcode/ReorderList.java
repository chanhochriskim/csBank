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

// sep 26. stack, upto half, zigzag.
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
        if (head == null || head.next == null) return;
        // stack method
        // 1. put everything inside stack.
        // 2. using loop until half the stack count, zig zag. 
        ListNode curr = head;
        Stack<ListNode> stack = new Stack();
        int count = 0;

        // stack prep.
        while (curr != null) {
            stack.add(curr);
            curr = curr.next;
            count++;
        }
        curr = head; // should be bakc to head from the end. (cuz we are reusing it)

        // zigzag.
        for (int i = 0; i < count/2; i++) {
            ListNode next = curr.next;
            ListNode last = stack.pop();
            curr.next = last;
            last.next = next;
            curr = next; 
        }

        curr.next = null;
    }
}
