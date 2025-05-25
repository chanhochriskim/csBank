class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i]; // s = "tan"
            char[] token = s.toCharArray(); // c = "t", "a", "n"
            Arrays.sort(token); // sort c --> "a", "n", "t" 
            String sorted = String.valueOf(token); // change it back to string --> ant. (will be used as a key)

            // sorted --> "key" to check whether it exists inside HashMap
            if (map.containsKey(sorted)) {
                map.get(sorted).add(s);
            } else { // if brand new, then create new key then add it. 
                map.put(sorted, new ArrayList());
                map.get(sorted).add(s);
            }

        }

        return new ArrayList<>(map.values());
    }
}
