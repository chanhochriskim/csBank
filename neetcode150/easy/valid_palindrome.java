// nov 3 -- 9:50am ~ 9:54am
/*
- limit to alphanumeric. use 2 pointers, then if differs, return false

*/

class Solution {
    public boolean isPalindrome(String s) {
        String real = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(real);
        int i = 0;
        int j = real.length() - 1;
        while (i <= j) {
            if (real.charAt(i) != real.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }        

        return true;
    }
}
