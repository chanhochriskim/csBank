// jan 22 - 8:50pm ~ 9:05pm
// use stack. 

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack();
        String[] values = path.split("/");
        String ans;

        for (int i = 0; i < values.length; i++) {
            String string = values[i];
            if (string.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!string.equals("/") && !string.equals(".") && !string.equals("")) {
                stack.add(string);
            }
        }      
        ans = "/" + String.join("/", stack);

        return ans;
    }
}
