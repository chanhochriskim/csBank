// dec 26 -- 11:35am ~ 12:45pm (took me a fucking 70 min damn)
/*
- neetcode solution O(n) , two hashmaps, checking their have == need count.
*/

class Solution {
    public String minWindow(String s, String t) {
        if (t.length() == 0) {
            return "";
        }
        Map<Character, Integer> window = new HashMap();
        Map<Character, Integer> countT = new HashMap();
        for (int i = 0; i < t.length(); i++) {
            countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
        }

        int need = countT.size(); // currently need value.
        int have = 0; // starting at 0 (duh)

        int[] res = new int[2]; // window range []
        int resLen = Integer.MAX_VALUE; // for math.min
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);
            // if key exists within count & their values match (increment have value)
            if (countT.containsKey(c) && countT.get(c).equals(window.get(c))) {
                have++;
            }
            while (have == need) {
                // if fulfilled the full requirement --> window result update
                if ((r - l + 1) < resLen) {
                    res[0] = l;
                    res[1] = r;
                    resLen = (r - l + 1);
                }
                // pop form the left of the window
                window.put(s.charAt(l), window.get(s.charAt(l)) - 1);
                if (countT.containsKey(s.charAt(l)) && window.get(s.charAt(l)) < countT.get(s.charAt(l))) {
                    have--;
                }
                l++;
            }
        }
        l = res[0];
        int r = res[1];
        
        return (resLen != Integer.MAX_VALUE) ? s.substring(l, r + 1) : "";
    }
}
