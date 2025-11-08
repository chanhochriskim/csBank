// nov 8 - 10:25am ~ 11:00am
/* analysis O(n*m)
* issue * 
--> line 33: for (int j = 0; j < s1.length() && a < s2.length(); j++ i was accidentally messing the s1 and s2.
--> line 42: if (map.get(c) != count[c - 'a'])  --> i was doing s2.charat(c), not just c

1. map the letter of s1. (ex: a:1 b:1)
2. loop over s2. if s2.charAt(i) contained in s1, set window frame to be s1.length
3. do the count, value matching, if good, return true.

*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() == 0) return false;

        // 1. map letters of s1.
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        // 2. loop over s2. 
        for (int i = 0; i < s2.length(); i++) {
            int[] count = new int[26];
            if (map.containsKey(s2.charAt(i))) {
                // triggered. check whether it is permutation. if so, true.
                int a = i;
                // get the count ready.
                for (int j = 0; j < s1.length() && a < s2.length(); j++) {
                    count[s2.charAt(a) - 'a']++; // [1,1,0,0,0....]
                    a++;
                }
                // now, compare the count with map one. if values match, true
                boolean check = true;
                for (char c: map.keySet()) {
                    if (map.get(c) != count[c - 'a']) {
                        check = false;
                    }
                }
                if (check == true) return true;
            }
        }

        return false;     
    }
}
