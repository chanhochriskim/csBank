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

// sep 16 
/* intuition -->
1. use int[] count to keep track of each alphabet. 
2. then, if window gets too big to handle the other letters, 
3. shift the left to keep the proper window. 

*/

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0; // window
        int maxChecker = 0; // k checker
        int res = 0; // max answer

        int[] count = new int[26]; // a ~ b letter count. 

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'A']++; // increment the count.
            maxChecker = Math.max(maxChecker, count[s.charAt(i) - 'A']); // get the current most valued letter. ex: AABABBA --> 4th, B. A = 3, B = 2. so max = 3. 

            while ((i - left + 1 - maxChecker) > k) { // 3 > 2, we can't handle all.
                // shift left
                count[s.charAt(left) - 'A']--; 
                left++; // shift left to the right
            }

            res = Math.max(res, i - left + 1);
        }

        return res;
    }
}
