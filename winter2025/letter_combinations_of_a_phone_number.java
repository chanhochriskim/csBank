// dec 21 - 12:45pm ~ 1:40pm (at least now i understood how backtracking works)
/* r.c: O(n * 4^n)... string length n * longest combi of 4 
backtracking
    - if curStr length = digits length --> append to ans.
    - contimue backtracking via loop.

1. use hashmap<char, str> 
2. backtrack(res array, map, digits, 0 (index), "" (curr_str))
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if (digits == null || digits.length() == 0) return res;

        HashMap<Character, String> map = new HashMap();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(res, map, digits, 0, "");

        return res;
    }

    public void backtrack(List<String> res, HashMap<Character, String> map, String digits, int index, String curr) {
        if (index == digits.length()) {
            res.add(curr);
            return;
        }
        String letters = map.get(digits.charAt(index)); // combi for each # ex: 2: abc
        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            backtrack(res, map, digits, index + 1, curr + c);
        }
    }
}
