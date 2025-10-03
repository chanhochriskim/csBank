// oct 3
/*
analysis:
come up with 3 privates -- global capacity, HashMap cache, LinkedList lruList
and set up the Node. (key, value)

LRUCache then initiates with capacity, cache, lruList.

get: 
if key exists within map (cache), declare Node by getting cache.get(key).
then, update lruList. remove key then addFirst. // updating. (left side = MRU, right = LRU)
return node.value (-1 if not exist)

put:
if key exists within map (cache), declare Node. 
lruList remove that node, node.value = value, then put it back to lruList.
if not (doesn't exist)
check capacity. if overload, remove LRU. removeLast(). cache.remove(node.key)
then, add new pair.
lruList.add()
cache.put()

*/

class LRUCache {
    private int capacity;
    private Map<Integer, Node> cache;
    private LinkedList<Node> lruList;

    private class Node {
        int key;
        int value;
        Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    } 

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap();
        lruList = new LinkedList();
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            lruList.remove(node);
            lruList.addFirst(node);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            lruList.remove(node);
            node.value = value;
            lruList.addFirst(node); // new node, value updated.
        } else {
            if (cache.size() >= capacity) {
                Node node = lruList.removeLast();
                cache.remove(node.key);
            }
            Node node = new Node(key, value);
            lruList.addFirst(node);
            cache.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
