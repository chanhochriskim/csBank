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

// sep 22: 12:05 
/* 
<< tortoise and hare algorithm >>
1. detect the cycle (very straightforard)
2. algo: use another pointer (slow2) at the head. when it meets with slow, that means that's the beginning part. 
--> t.c O(n), but Space compelxity would be O(1) since we use pointers in floyd's algo, instead of using hash table.
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
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break; // found the beginning part. break.
            }

        } 
        ListNode slow2 = head; // new pointer. 

        while (slow.next != null) {
            // i mean, it's confirmed to be not null but still.
            if (slow == slow2) return slow;
            slow = slow.next;
            slow2 = slow2.next;
        }

        return null;
    }
}
