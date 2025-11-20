// nov 20 - 9:55am ~ 10:35am

/* T.C = O(n) / S.C = O(1) --> char checker
- 2 ptrs --> i = reader / j = writer
- for each i, go over the char array for the same letter & increment the count
- once out of the that while loop, using j & Stringbuilder, append the next val if count > 1.
- after going over, return j (since j holds the updated length)
*/

class Solution {
    public int compress(char[] chars) {
        int i = 0;
        int j = 0;
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        while (i < len) {
            int count = 1;
            char c = chars[i]; // c = 'a'
            while (i < len - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            chars[j++] = c; // 0th = 'a' ['a',_,_,_,...]
            if (count > 1) {
                sb.append(count);
                while (sb.length() > 0) { // forgot this while loop in case count > 9
                    chars[j++] = sb.charAt(0);
                    sb.deleteCharAt(0);
                }
            }
            i++;
        }
        return j;
    }
}
