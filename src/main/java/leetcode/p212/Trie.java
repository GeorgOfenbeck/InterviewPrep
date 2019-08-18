package leetcode.p212;

import java.util.HashMap;
import java.util.LinkedList;

class Node {
    HashMap<Character, Node> children;
    Character val;
    Node parent;

    Node(Node parent, Character val) {
        children = new HashMap<>();
        this.parent = parent;
        this.val = val;
    }
}

public class Trie {
    Node dummy = new Node(null, '*');

    void addWord(String word) {
        LinkedList<Character> chars = new LinkedList<>();
        for (int i = 0; i < word.length(); i++)
            chars.addLast(word.charAt(i));
        recurse(chars, dummy);
    }


    void recurse(LinkedList<Character> chars, Node node) {
        if (chars.isEmpty()) {
            node.children.putIfAbsent('*', new Node(node, '*'));
        } else {
            Character cur = chars.pollFirst();
            node.children.putIfAbsent(cur, new Node(node, cur));
            recurse(chars, node.children.get(cur));
        }
    }

    Boolean contains(Character c, Node trie) {
        return trie.children.containsKey(c);
    }

    Node getPostFixTrie(Character c, Node trie) {
        if (trie.children.containsKey(c))
            return trie.children.get(c);
        else return null;
    }

    String getWord(Node trie) {
        Node cur = trie;
        StringBuilder sb = new StringBuilder();
        while (cur != null && cur.val != '*') {
            sb.append(cur.val);
            cur = cur.parent;
        }
        return sb.reverse().toString();
    }

    void removeWord(Node trie) {
        Node cur = trie;
        while (cur != null && cur.children.size() == 0) {
            Character toremove = cur.val;
            cur = cur.parent;
            if (cur != null)
                cur.children.remove(toremove);
        }

    }


}
