// jan 4 -- 9:10pm ~ 9:30pm
// pretty much the same as vanilla Trie problem, but only that this time we had to consider '.' value.
class WordDictionary {
    boolean isEnd;
    WordDictionary[] children;

    public WordDictionary() {
        isEnd = false;
        children = new WordDictionary[26];
    }
    
    public void addWord(String word) {
        WordDictionary wd = this;
        for (char c : word.toCharArray()) {
            if (wd.children[c - 'a'] == null) {
                wd.children[c - 'a'] = new WordDictionary();
            }
            wd = wd.children[c - 'a'];
        }
        wd.isEnd = true;
    }
    
    public boolean search(String word) {
        return helper(this, word, 0);
    }

    public boolean helper(WordDictionary node, String word, int index) {
        WordDictionary curr = node;
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (WordDictionary child : curr.children) {
                    if (child != null && helper(child, word, i + 1)) return true;
                }
                return false;
            }

            if (curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr.isEnd;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
