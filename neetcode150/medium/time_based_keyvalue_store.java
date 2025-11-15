// nov 15 -- 2:55pm ~ 3:10pm
// java solution: Ahmed Nasr 
/*
2 classes
----------
- Pair --> holds 2 values: timestamp / value. (key-value within timestam)
- TimeMap --> contains HashMap (keys / pair objects)
----------
set: if key already exists --> appends / if not, make a new one
get: binary search within list of key-value pairs using cand (candidate) value. 
---> get part.. needs an exaplanation 
*/

class Pair {
    int timestamp; 
    String val;

    Pair(int timestamp, String val) {
        this.timestamp = timestamp;
        this.val = val;
    }
}

class TimeMap {

    private HashMap<String, ArrayList<Pair>> hashMap;

    public TimeMap() {
        hashMap = new HashMap();
    }
    
    public void set(String key, String value, int timestamp) {
        if (hashMap.containsKey(key)) {
            hashMap.get(key).add(new Pair(timestamp, value)); // replace with new timestamp
        } else {
            ArrayList<Pair> arr = new ArrayList();
            arr.add(new Pair(timestamp, value));
            hashMap.put(key, arr);
        }
    }
    
    public String get(String key, int timestamp) {
        String cand = "";
        if (hashMap.containsKey(key)) {
            ArrayList<Pair> arr = hashMap.get(key);
            int a = 0;
            int b = arr.size() - 1;
            while (a <= b) {
                int mid = (a + b) / 2;
                int timeVal = arr.get(mid).timestamp;
                if (timeVal == timestamp) {
                    return arr.get(mid).val;
                } else if (timeVal < timestamp) {
                    cand = arr.get(a).val;
                    a = mid + 1;
                } else {
                    b = mid - 1;
                }
            } 
        }
        return cand;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
