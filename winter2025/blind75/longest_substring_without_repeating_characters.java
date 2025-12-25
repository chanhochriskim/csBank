// dec 24 - 9:58pm ~ 10:01pm (heck yeah!)

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet();
        int a = 0;
        int b = 0;
        int ans = 0;

        while (b != s.length()) {
            if (!set.contains(s.charAt(b))) {
                set.add(s.charAt(b));
                b++;
                ans = Math.max(ans, b - a);
            } else {
                while (set.contains(s.charAt(b))) {
                    set.remove(s.charAt(a));
                    a++;
                }
            }
        }

        return ans;
    }
}
