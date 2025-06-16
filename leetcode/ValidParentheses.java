// og code but ugly af
class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0)
            return true;
        if (s.length() == 1)
            return false;
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            if (!stack.empty()) {
                if (c == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == ']') {
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == '}') {
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            } else if (stack.empty() && (c == ')' || c == ']' || c == '}')) {
                return false;
            }
            if (c == '(' || c == '[' || c == '{') { // c is either (, [, {
                stack.push(c);
            }
        }
        if (!stack.empty())
            return false;

        return true;
    }
}
