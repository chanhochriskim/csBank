// mar 27 - 8:15pm ~ 9:15pm
// rc: O(m+n) spacE: o(1)
/*

1. 2 maps (window, countT)
2. fill out countT
3. 2 ints (need --> countT length, have) &  int[] res = int[2] (range), resLen, left
4. for-loop through s
    - window put (s.at(r))
    - if character match --> have++
    - while(have == need)
        - if (r-l+a < resLen) update res & resLen
        - window decrement s.at(l)
        - if (count.contains(s.at(l)) && window.get(s.at(l)) < count.get(s.at(l)))
            - have--
        l++ (advance)
5. l & r, return string.
*/

class Solution {
    public String minWindow(String s, String t) {
        if (t.length() == 0)
            return "";

        Map<Character, Integer> window = new HashMap();
        Map<Character, Integer> count = new HashMap();

        // first fill out count map. 
        for (int i = 0; i < t.length(); i++) {
            count.put(t.charAt(i), count.getOrDefault(t.charAt(i), 0) + 1);
        }

        int[] res = new int[2]; // for left, right
        int resLen = Integer.MAX_VALUE; // length comparison
        int l = 0; // pointer

        int need = count.size(); 
        int have = 0;

        for (int r = 0; r < s.length(); r++) {
            // add r value to the window map
            window.put(s.charAt(r), window.getOrDefault(s.charAt(r), 0) + 1);

            // if count also has that value? have++
            if (count.containsKey(s.charAt(r)) && count.get(s.charAt(r)).equals(window.get(s.charAt(r)))) {
                have++;
            }

            // when have == need (minimize size (resLen) && decrement that l value from window && compare if it affects have count)
            while (have == need) {
                if (r - l + 1 < resLen) {
                    res[0] = l;
                    res[1] = r;
                    resLen = r - l + 1;
                }
                window.put(s.charAt(l), window.get(s.charAt(l)) - 1);
                if (count.containsKey(s.charAt(l)) && window.get(s.charAt(l)) < count.get(s.charAt(l))) {
                    have--;
                }
                l++; // l increments
            }
        }
        l = res[0]; 
        int r = res[1]; 
        return (resLen != Integer.MAX_VALUE) ? s.substring(l, r + 1) : "";
    }
}
