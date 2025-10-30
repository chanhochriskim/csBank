// oct 30 -- neetcode 150 -- 9:25am ~ 9:35am
/*
- string equals wouldn't work even with string to string since it gives memory reference, which means it won't return the right value. so just do arrays.equal 


*/

class Solution {
    public boolean isAnagram(String s, String t) {
        char[] c = new char[s.length()];
        char[] b = new char[t.length()];
        c = s.toCharArray();
        Arrays.sort(c);

        b = t.toCharArray();
        Arrays.sort(b);

        return Arrays.equals(c, b);
    }
}
