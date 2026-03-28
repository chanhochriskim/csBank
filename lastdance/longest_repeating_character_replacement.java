// mar 27 - 11:55pm ~ 12:10am
// AABABBA  

class Solution {
    public int characterReplacement(String s, int k) {
        if (s.length() == 1) return 1;
        int l = 0;
        int freq = 0;
        int longest = 0;
        int[] num = new int[26];
        for (int r = 0; r < s.length(); r++) {
            num[s.charAt(r) - 'A']++;
            freq = Math.max(freq, num[s.charAt(r) - 'A']);

            while (r - l + 1 - freq > k) {
                num[s.charAt(l) - 'A']--;
                l++;
            }
            longest = Math.max(longest, r - l + 1);
        }

        return longest;
    }
}
