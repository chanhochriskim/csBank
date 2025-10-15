// oct 14: 8:00pm - 8:10pm

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) return new ArrayList();
        HashMap<String, List<String>> map = new HashMap();
        
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String sorted = new String(c); // "aet"

            if (!map.containsKey(sorted)) {
               map.put(sorted, new ArrayList<>());
            } 
            map.get(sorted).add(s);
        }


        return new ArrayList<>(map.values());
    }
}
