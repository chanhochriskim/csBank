// nov 5 -- 9:10am ~ 9:15am
/* analysis 
- using set, get rid of duplicate.
- if duplicate NOT detected, b goes along & add the value to the set
- if there IS a duplicate, using the while loop, increment a until there's none.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int best = 0;
        int a = 0;
        int b = 0;
        Set<Character> set = new HashSet();

        while (b != s.length()) {
            if (!set.contains(s.charAt(b))) {
                set.add(s.charAt(b));
                b++;
                best = Math.max(best, b - a);
            } else {
                while (set.contains(s.charAt(b))) {
                    set.remove(s.charAt(a));
                    a++;
                }
            }
        }


        return best;        
    }
}
