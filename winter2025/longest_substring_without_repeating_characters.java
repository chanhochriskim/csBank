// dec 18 - 9:20am ~ 9:30am
/*
intuition was there but failing edge cases.
*ojos*
1. within adding value, b++ shoud be done first before math.max count.
2. second a-removal while loop, it should be s.charat(b), since that's where a should be reached.

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet();
        int a = 0;
        int b = 0;
        int count = 0;

        while (b < s.length()) {
            if (!set.contains(s.charAt(b))) {
                set.add(s.charAt(b));
                b++;
                count = Math.max(count, b - a);
            } else {
                while (set.contains(s.charAt(b))) {
                    set.remove(s.charAt(a));
                    a++;
                }
            }
        }

        return count;
    }
}
