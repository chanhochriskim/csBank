// nov 9 -- 10:45am ~ 11:10am
/*
to make everything to be O(1), for getmin, we will also be tracking it during push

*/

class Node {
    public int data;
    public int min; 
    public Node next;

    public Node (int x, int currMin) {
        data = x;
        min = currMin; // for o(1), always track min
        next = null;
    }
}

class MinStack {
    private Node top; // pointer to top node.
    
    public MinStack() {
        // initializes the stack object   
        top = null; 
    }
    
    public void push(int val) {
        // push 'val' onto stack
        int currMin = val;
        if (top != null) {
            currMin = Math.min(top.min, val);
        }
        Node temp = new Node(val, currMin);
        temp.next = top;
        top = temp;
    }
    
    public void pop() {
        // removes element on top of stack
        Node temp = top;
        top = top.next;
        temp = null;
    }
    
    public int top() {
        // getes top element of stack
        return top.data;
    }
    
    public int getMin() {
        // retrieves the minimum element in stack in O(1).
        return top.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
