class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        longest = 0
        l = 0 
        counts = [0] * 26
        for r in range(len(s)):
            counts[ord(s[r]) - 65] += 1
            while (r - l + 1) - max(counts) > k:
                counts[ord(s[l]) - 65] -= 1
                l += 1
            
            longest = max(longest, (r - l + 1))

        return longest

// java version
class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int longest = 0; // longest answer
        int maxCount = 0; // k handler.
        int[] count = new int[26]; // alphabet counters

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']); 
            // <-- most frequent alphabet. (A)

            while ((right - left + 1 - maxCount) > k) { // if it goes over k limit
                count[s.charAt(left) - 'A']--;
                left++;
            }
            longest = Math.max(longest, right - left + 1); // current window
        }

        return longest;
    }
}
