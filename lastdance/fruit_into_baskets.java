// mar 27 - 11:05pm ~ 11:25pm
// rc: O(n) sliding window, sc: O(1) map stores at most 2 fruit types

class Solution {
    public int totalFruit(int[] fruits) {
        if (fruits.length == 1) return 1;
        Map<Integer, Integer> map = new HashMap(); 
        int best = 0;
        int l = 0;
        int max = 2;

        for (int r = 0; r < fruits.length; r++) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1); // i was doing double map.get here with fruits[r] lol...
            if (map.size() > max) { // bad, quota exceeded! must do something
                // decrement, if map.get == 0 --> remove.
                while (map.size() > max) {
                    map.put(fruits[l], map.get(fruits[l]) - 1);
                    if (map.get(fruits[l]) == 0) {
                        map.remove(fruits[l]);
                    }
                    l++;
                }
            }
            best = Math.max(best, r - l + 1);
            
        }

        return best;
    }
}
