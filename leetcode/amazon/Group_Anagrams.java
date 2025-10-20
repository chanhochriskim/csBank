// oct 19 -- 8:15pm ~ 8:25pm
/* analysis:
- using hashmap (key: sorted letter, value: original value)
- return the map values.
T.C: O(n * klogk) --> array length n + string length k + sorting logk
S.C: O(n * k) --> hashmap upto 'n' distinct sorted strings, each of string with max length 'k'
*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();

        // go over each, sort that str, compare it with hashmap. 
        for (String str: strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String sorted = String.valueOf(c); // "eat" => "aet"

            if (!map.containsKey(sorted)) { // make a new one since key not exist.
                map.put(sorted, new ArrayList<>());
            } 
            map.get(sorted).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
