// dec 23 - 9:15pm ~ 9:20pm

class Solution {
    public boolean isPalindrome(String s) {
        String sorted = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        int a = 0;
        int b = sorted.length() - 1;
        while (a < b) {
            if (sorted.charAt(a) != sorted.charAt(b)) {
                return false;
            } 
            a++;
            b--;
        }
        return true;
    }
}
