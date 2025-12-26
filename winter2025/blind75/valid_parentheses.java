// dec 25 -- merry christmas! 11:10pm ~ 11:15pm

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.add(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;
                if (s.charAt(i) == ')') {
                    if (stack.peek() != '(') {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else if (s.charAt(i) == ']') {
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
