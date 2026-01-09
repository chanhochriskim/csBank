// jan 9 -- 11:15am ~ 11:30am

/* analysis: Two Pointer & center approach 
1. come up with 2 points: start & end.
2. loop over s.length
    - use helper to get even & odd for certain string.
    - int len = math.max(even, odd) --> to compute the right length 
    - if len > end - start + 1 --> update start & end
        - start = i - ((len - 1) / 2) // ex: racecar 3 - ((7-1) / 2)
        - end = i + (len / 2); // 3 + (7/2) = 6
    
3. return s.substring(start, end + 1);

- helper(String s, int left, int right) {
    while (left >= 0, right <= s.length() - 1, s.charAt(left) == s.charAt(right)) {
        left-- & right++
    }
    return right - left - 1 // 7 - (-1) - 1= 7. 
}
*/

class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int even = helper(s, i, i + 1); 
            int odd = helper(s, i, i);
            int len = Math.max(even, odd);
            if (len > end - start + 1) { // ex: racecar
                start = i - ((len - 1) / 2); // 3 - 6/2 = 3-3=0
                end = i + (len / 2); // 3 + (7/2) = 6
            }
        }

        return s.substring(start, end + 1);
    }

    public int helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 7 - (-1) - 1= 7
    }
}
