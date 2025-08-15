// airport ~45min. nick white helped.

// thought process: 
/*
get the helper to retrieve the right value. it will expand outward. 
on the main, len1 is used to handle odd case, where len2 is to handle even case. 
using these 2 vlues, we will get the proper start and end point, then return the substring of it. 
** my error was doing s.substring(start, end), instead of start, end + 1. 
*/

class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        if (s == null || s.length() == 0) return "";

        for (int i = 0; i < s.length(); i++) {
            int len1 = helper(s, i, i); // odd case "racecar"
            int len2 = helper(s, i, i + 1); // even case "aabbaa" 
            int len = Math.max(len1, len2);
            if (len > start + end) {
                start = i - ((len - 1) / 2);
                end = i + (len / 2);
            }
        }
        return s.substring(start, end + 1);

    }

    public int helper(String s, int left, int right) {
        if (s == null || left > right) return 0; // edge case

        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1; // "racecar" --> 7 - (-1) - 1 = 7. 
    }
}
