// dec 24 -- 10:50pm ~ 11:05pm
/*
- int left, maxcount, longest, right.
- maxcount = current most frequent value within the window.
return longest.

*/

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int maxcount = 0;
        int longest = 0;
        int[] count = new int[26]; // a ~ z

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxcount = Math.max(maxcount, count[s.charAt(right) - 'A']);

            while ((right - left + 1 - maxcount) > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }
}

