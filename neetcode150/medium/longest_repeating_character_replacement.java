// nov 6 -- 10:30am ~ 11:10am
/* analysis O(n) sliding window
- using int[] letter count array, we can get to track the #. 
- if goes over a limit, adjust them accordingly.

*/

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int longest = 0;
        int maxCount = 0;
        int[] count = new int[26];

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']); // most frequent letter tracker.

            // ex: maxcount tracks the most letter within the frame. meaning, if it that trigger checks the frame is more than what k can handle, that's bad, so we have to shrink
            while ((right - left + 1 - maxCount) > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }
}
