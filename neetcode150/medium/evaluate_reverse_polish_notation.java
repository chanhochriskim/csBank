// nov 10 - 9:10am ~ 9:30am
/* analysis
- if number, add to stack.
- if operand, check if at lesat 2 numbers inside stack. if not, -1 

*/
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        int a;
        int b;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
              a = stack.pop();
              b = stack.pop();
              stack.add(a + b); 
            } else if (tokens[i].equals("-")) {
                a = stack.pop();
                b = stack.pop();
                stack.add(b - a);
            } else if (tokens[i].equals("*")) {
                a = stack.pop();
                b = stack.pop();
                stack.add(a * b);
            } else if (tokens[i].equals("/")) {
                a = stack.pop();
                b = stack.pop();
                stack.add(b / a);
            } else {
                stack.add(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}
