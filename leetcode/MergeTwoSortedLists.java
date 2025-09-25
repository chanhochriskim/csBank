// 40:15 had to look over the prev answer for full understanding.
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp = new ListNode(0);
        ListNode curr = temp; 

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                // list1 is bigger. link to list2
                curr.next = list2;
                list2 = list2.next;
            } else {
                // if list1 is equal or smaller than list2, then link to list2
                curr.next = list1;
                list1 = list1.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
            list1 = list1.next;
        } 
        if (list2 != null) {
            curr.next = list2;
            list2 = list2.next;
        }

        return temp.next; // linked via Next Node.
    }
}


// sep 25 -- 10:23 ~ 

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp = new ListNode(0);
        ListNode curr = temp; 

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) { // go with list1 val
                curr.next = list1;
                list1 = list1.next;
                curr = curr.next;
            } else { // list 2 smaller. go with it.
                curr.next = list2;
                list2 = list2.next;
                curr = curr.next;
            }
        }

        // proceed with the rest if not finished
        if (list1 != null) {  
            curr.next = list1;
            list1 = list1.next;
            curr = curr.next;
        }

        if (list2 != null) {
            curr.next = list2;
            list2 = list2.next;
            curr = curr.next;
        }

        return temp.next;
    }
}
