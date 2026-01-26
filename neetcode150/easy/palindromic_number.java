// jan 26 - 5:15pm ~ 5:20pm

class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        char[] c = s.toCharArray();
        if (c.length == 1) return true;
        if (c[0] == '-') return false;
        
        int a = 0;
        int b = c.length - 1;
        while (a < b) {
            if (c[a] != c[b]) {
                return false;
            } else {
                a++;
                b--;
            }
        } 
        return true;
    }
}
