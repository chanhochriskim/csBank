// jul 19 2025. about 30min, but private search part needed some assistant. 

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

class WordDictionary {
    WordDictionary[] children;
    boolean isEnd; // is it really needed? not sure.

    public WordDictionary() {
        children = new WordDictionary[26];
        isEnd = false;
    }

    public void addWord(String word) {
        WordDictionary curr = this;

        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new WordDictionary();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true; // end of the word. 
    }

    public boolean search(String word) {
        return searchFrom(this, word, 0);
    }

    private boolean searchFrom(WordDictionary node, String word, int index) {
        WordDictionary curr = node;
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (WordDictionary child : curr.children) {
                    if (child != null && searchFrom(child, word, i + 1)) {
                        return true;
                    }
                }
                return false;
            }
            if (curr.children[c - 'a'] == null)
                return false;
            curr = curr.children[c - 'a'];
        }
        return curr.isEnd;
    }

}
