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

// sep 10
class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            //--> start form very beginning, then it will go through.
            int odd = expandfrommiddle(s, i, i); // odd racecar // the very middle one will not have anything identical so.
            int even = expandfrommiddle(s, i, i+1); // even aabbaa  

            int len = Math.max(odd, even); // actual length 

            if (len > start + end) { // if len is bigger,
                // gotta update the start & end part accordingly.
                start = i - ((len - 1) / 2); // racecar --> 3 - (3) = 0
                end = i + (len / 2); // 3 + 3 = 6.
            }
        }

        return s.substring(start, end + 1); // 0, 6 + 1 --> ans = racecar.
    }

    public int expandfrommiddle(String s, int left, int right) {
        // returns # of how much. 
        if (s == null || left > right) return 0;

        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1; // 7 - (-1) - 1 = 7.
    }
}

// sep 13 -- problem was the if (len >.. part)

class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int even = length(s, i, i + 1); // aabbaa
            int odd = length(s, i, i); // racecar

            int len = Math.max(even, odd); // get whatever the right value.

            if (len > (end - start + 1)) { // <-- *problem was i was doing len > end + start, instead of getting accurate length.
                // if len is greater, re-adjust the 
                start = i - ((len - 1) / 2); // 3 - ((7 - 1) / 2) = 0
                end = i + (len / 2); // 3 + (7 / 2 + 1) = 6 + 1;
            }
        } 
        return s.substring(start, end + 1);
    }


    // helper that gets the length of each
    public static int length(String s, int left, int right) {
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1; // 7 - (-1) - 1 = 7.
    }
}
