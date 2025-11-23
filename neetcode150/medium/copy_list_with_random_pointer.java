// nov 23 -- 3:00pm ~ 3:30pm
/*
- using HashMap, loop twice for first: get the copy of nodes, and for second: get next & random pointers.
*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap(); // oldToCopy
        Node curr = head;

        while (curr != null) {
            map.put(curr, new Node(curr.val)); // all node = get values.
            curr = curr.next;
        }
        curr = head;

        while (curr != null) { // assign next & random values
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head); // map.get(head) = cloned head.
    }
}
