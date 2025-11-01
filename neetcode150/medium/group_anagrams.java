// nov 1 - 11:25am ~ 11:35am
/* analysis:
- using hashmap (key: sorted letter, value: original value)
- return the map values.

*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap();

        for (String str: strs) {
            char[] c = str.toCharArray(); // sort: eat --> e a t
            Arrays.sort(c); // a e t
            String sorted = String.valueOf(c); // a e t --> aet. 
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList()); // aet: eat, tea, 
            }
            map.get(sorted).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
