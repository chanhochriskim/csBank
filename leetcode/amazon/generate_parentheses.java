// dec 16 - 7:50pm - 8:25pm 
/* O(4 N /Sqrt(N)) tf lmao
- only add open if open < n.
- only add closing if closed < open.
- valid IIF open == closed && they are the size of n. 
    for recursive loop, I had to use res.add(String.join("", stack)); to merge ( and ) as "()"
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        Stack<String> stack = new Stack();
        List<String> res = new ArrayList();
        int openN = 0;
        int closedN = 0;
        backtrack(stack, res, openN, closedN, n);

        return res;         
    }

    public void backtrack(Stack<String> stack, List<String> res, int openN, int closedN, int n) {
        if (openN == closedN && openN == n) {
            res.add(String.join("", stack));
            return;
        }

        if (openN < n) {
            stack.push("(");
            backtrack(stack, res, openN + 1, closedN, n);
            stack.pop();
        }

        if (closedN < openN) {
            stack.push(")");
            backtrack(stack, res, openN, closedN + 1, n);
            stack.pop();
        }
    }
}
