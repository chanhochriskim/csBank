// dec 21 - 10:50am ~ 11:10am

/* O(4 N /Sqrt(N)) tf lmao
- only add open if open < n.
- only add closing if closed < open.
- valid IIF open == closed && they are the size of n. 
    for recursive loop, I had to use res.add(String.join("", stack)); to merge ( and ) as "()"
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        Stack<String> stack = new Stack();
        List<String> list = new ArrayList();
        int open = 0;
        int closed = 0;
        helper(stack, list, open, closed, n);
        return list;
    }

    public void helper(Stack<String> stack, List<String> list, int open, int closed, int n) {
        if (open < n) {
            stack.add("(");
            helper(stack, list, open + 1, closed, n);
            stack.pop(); // pop so that it can properly backtrack recursively
        }
        if (closed < open) {
            stack.add(")");
            helper(stack, list, open, closed + 1, n);
            stack.pop(); 
        }
        
        if (open == n && closed == open) {
            list.add(String.join("", stack));
        }
    }
}
