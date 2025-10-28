// oct 28 - AWS Prep, Corec, 2:30pm ~ 2:50pm 
/* analysis O(max(m,n)) --> basically O(n) of whichever the linked list is longer.
1. declare dummy & another linked list (l3) & carry integer 
2. go over each node, sum up the digits, if over 10, carry over it to the next sequence
3. then, update l3 & l1 l2 l3 to advance to the next node. 
4. after the loop, if carry is >0, then use that last carry to update the l3. 
5. return dummy.next; (l3)

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode l3 = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // get l1 value, l2 value, then summed value along with carry part.
            int l1_val = (l1 != null) ? l1.val : 0;
            int l2_val = (l2 != null) ? l2.val : 0;
            int current_sum = l1_val + l2_val + carry; 
            carry = current_sum / 10; // if more than 10, 1 carried over to the next.
            int digit = current_sum % 10; // the actual digit node.

            // now, do the l3 work
            ListNode new_node = new ListNode(digit);
            l3.next = new_node;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            l3 = l3.next;
        }

        // if carry still remains
        if (carry > 0) {
            ListNode new_node = new ListNode(carry);
            l3.next = new_node;
            l3 = l3.next;
        }

        return dummy.next;
    }
}
