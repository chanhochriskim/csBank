// mar 27 - 6:05pm ~ 6:40pm

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) return s.length();
        int a = 0; // left pointer (cleaner)
        Map<Integer, Integer> map = new HashMap(); // uniqueness checker
        int best = 1;

        for (int b = 0; b < s.length(); b++) {
            if (!map.containsKey(s.charAt(b) - 'a')) { 
                map.put(s.charAt(b) - 'a', map.getOrDefault(s.charAt(b) - 'a', 0) + 1);
                best = Math.max(best, b - a + 1);
            } else {
                while (map.get(s.charAt(b) - 'a') != 0) {
                    int curr = map.get(s.charAt(a) - 'a');
                    map.put(s.charAt(a) - 'a', curr - 1);
                    a++;
                }
                map.put(s.charAt(b) - 'a', map.getOrDefault(s.charAt(b) - 'a', 0) + 1);
                best = Math.max(best, b - a + 1);
            }
        }

        return best;
    }
}
