// jan 4 - 8:25pm ~ 8:45pm 
// a bit of help from geekforgeeks and past submission
class Trie {
    boolean isEnd;
    Trie[] children;

    public Trie() {
        isEnd = false;
        children = new Trie[26];
    }
    
    public void insert(String word) {
        Trie trie = this; // initialization
        for (char c : word.toCharArray()) {
            if (trie.children[c - 'a'] == null) {
                trie.children[c - 'a'] = new Trie();
            }
            trie = trie.children[c - 'a'];
        }
        trie.isEnd = true;
    }
    
    public boolean search(String word) {
        Trie trie = this;
        for (char c : word.toCharArray()) {
            if (trie.children[c - 'a'] == null) return false; 
            trie = trie.children[c - 'a'];
        }
        if (trie.isEnd == true) {
            return true;
        }
        return false;
    }
    
    public boolean startsWith(String prefix) {
        Trie trie = this;
        for (char c : prefix.toCharArray()) {
            if (trie.children[c - 'a'] == null) return false; 
            trie = trie.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
