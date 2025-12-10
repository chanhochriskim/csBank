// dec 10 -- 10:00am ~ 10:15am
/*
- initialize empty strinig 'ans' to store the common prefix
- sort the input list lexicographically (to find common ones among first / last strings in sorted list)
- iterate through the characters (of first / last strings), stop at length of shorter string
- if letter differ, return the common prefix found so far.
- otherwise, append the current character to the ans string
- return 'ans' string
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();
        Arrays.sort(strs); // lexicograhpical sort ex: [apple][cat][dog]
        String first = strs[0];
        String last = strs[strs.length - 1];

        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            } else {
                ans.append(first.charAt(i));
            }
        }
        return ans.toString();
    }
}
