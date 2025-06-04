class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        int i = 0;
        int j = s.length() - 1; // i starts from the end
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } 
            i++;
            j--;
        }


        return true;
    }
}
