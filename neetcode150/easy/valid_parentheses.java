// nov 8 -- 8:50pm ~ 8.58pm 
/* analysis O(n) --> string length
- using stack, if opener, add to stack. if closer, compare with top. 
- if match, pop, if not, false.
- after going over s.length, return true if nothing goes wrong.

*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if (c == ')') {
                    if (stack.peek() != '(') {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else if (c == ']') {
                    if (stack.peek() != '[') {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else {
                    if (stack.peek() != '{') {
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }
        }   
        return stack.isEmpty();
    }
}
