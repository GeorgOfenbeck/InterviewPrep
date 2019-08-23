package leetcode.p820;

class Trie{
    Trie[] children;
    int nrchildren;

    Trie(){
        children = new Trie[28];
        nrchildren = 0;
    }

    public static void addToTrie(Trie root,String word){
        if (word == "") return;
        Trie cur = root;
        for (int i = word.length()-1; i >= 0; i--){
            char c = word.charAt(i);
            int pos = (int) c - (int) 'a';
            if (cur.children[pos] == null){
                cur.children[pos] = new Trie();
            }
            cur.nrchildren++;
            cur = cur.children[pos];
        }
        cur.children[27] = cur.children[27]; //end tag
    }
}

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        for (int i = 0; i < words.length; i ++){
            Trie.addToTrie(root,words[i]);
        }

        
    }
}
