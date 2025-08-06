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

// aug 6 (new trial) --> everything was fine except that i didn't know how to do a proper return new arraylist<>(map.values()) part.
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();

        char[] charArray;
        for (String str : strs) {
            charArray = str.toCharArray(); 
            Arrays.sort(charArray); 
            String sorted = String.valueOf(charArray); // now sorted [eat -> aet]

            // if that key doesn't exist, make a new list
            if (!map.containsKey(sorted)) { 
                map.put(sorted, new ArrayList<String>());
                map.get(sorted).add(str);
            } else {
                map.get(sorted).add(str);
            }
        }        
        // map.values() --> all work properly.

        return new ArrayList(map.values());
    }
}

// aug 6 python ver. THIS EASY AND SIMPLE WHAT??
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # dictionary (auto-creates empty lists)
        anagram_map = defaultdict(list) 

        # loop (each word in input list)
        for word in strs:
            # sort string to form a key
            sorted_val = ''.join(sorted(word)) # '' join means no separater

            # append original word to correct group
            anagram_map[sorted_val].append(word)
        
        return list(anagram_map.values())

        
