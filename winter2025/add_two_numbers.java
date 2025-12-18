// dec 18 -- 5:00pm ~ 5:05pm

// --> same description as last time!
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
        
        // while either one's not null. 
        while (l1 != null || l2 != null) {
            int l1_val = (l1 != null) ? l1.val : 0;
            int l2_val = (l2 != null) ? l2.val : 0;
            int l3_val = l1_val + l2_val + carry; 

            carry = l3_val / 10;
            int val = l3_val % 10;
            ListNode temp = new ListNode(val);
            l3.next = temp; 

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            l3 = l3.next;
        }

        if (carry > 0) {
            ListNode temp = new ListNode(carry);
            l3.next = temp;
            l3 = l3.next;
        }

        return dummy.next; // l3
    }
}
