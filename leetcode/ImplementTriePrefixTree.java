// jul 19 2025 (coffeeBean cafe) -- took ~23:15. i was trynna do trie root, but didnt work.
// instead, there's this thing called 'this', which i could replace root with it then resolved the issue.

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class Trie {
    Trie[] children;
    boolean isEnd;

    public Trie() { // initialize the trie object. 
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) { // inserts string word into the trie
        // initialize with the curr to be the root & loop through. 
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new Trie();
            }
            curr = curr.children[c - 'a']; // move along the loop.
        }
        curr.isEnd = true; // indicates the word ends HERE.
    }

    public boolean search(String word) { // true if non-prefix word exists. if not, false. 
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null)
                return false; // not exist.
            curr = curr.children[c - 'a'];
        }
        return curr.isEnd;
    }

    public boolean startsWith(String prefix) { // true if prefix exists. if not, false. 
        Trie curr = this;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - 'a'] == null)
                return false;
            curr = curr.children[c - 'a'];
        }
        return true;
    }
}
