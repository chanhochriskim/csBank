// dec 22 - 4:20pm ~ 4:23pm

class Solution {
    public boolean isAnagram(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return (Arrays.equals(a, b)) ? true : false;
    }
}
