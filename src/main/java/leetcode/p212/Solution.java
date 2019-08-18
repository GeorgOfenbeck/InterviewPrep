package leetcode.p212;


import java.awt.*;
import java.util.*;
import java.util.List;


class Solution {

    public static void main(String [] args){
        /*
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath","pea","eat","rain"};
         */
        char[][] board = {
                {'a'},
                {'a'}

        };
        String[] words = {"a"};
        Solution sol = new Solution();
        List<String> ll = sol.findWords(board,words);
        for (int i = 0; i < ll.size(); i++){
            System.out.println(ll.get(i));
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new ArrayList<>();
        Trie trie = new Trie();
        for (int k = 0; k < words.length; k++){
            trie.addWord(words[k]);
        }
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[i].length; j++){
                List<String> ll = findWord(trie,board,new Point(i,j));
                ret.addAll(ll);
            }
        }

        Comparator<String> cmp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        /*
        HashSet<String> hs = new HashSet<>();
        hs.addAll(ret);
        ret.clear();
        ret.addAll(hs);*/
        ret.sort(cmp);
        return ret;
    }

    List<String> findWord(Trie trie, char[][] board, Point startpoint){
        HashSet<Point> visited = new HashSet<>();
        Node postfix = trie.dummy;
        return recurse(trie,postfix,visited,new ArrayList<>(),startpoint,board);
    }

    List<String> recurse(Trie trie, Node postfix, HashSet<Point> visited, List<String> sofar, Point pos, char[][] board ){
        if (visited.contains(pos) || pos.x < 0 || pos.x >= board.length || pos.y < 0 || pos.y >= board[pos.x].length ){
            return sofar;
        } else {
            HashSet<Point> nvisited = new HashSet<>();
            nvisited.addAll(visited);
            nvisited.add(pos);
            Character focused = board[pos.x][pos.y];
            if (trie.contains(focused, postfix)) {
                Node ppostfix = trie.getPostFixTrie(focused, postfix);
                if (trie.contains('*', ppostfix)) {
                    sofar.add(trie.getWord(ppostfix));
                    trie.removeWord(trie.getPostFixTrie('*',ppostfix));
                }
                recurse(trie, ppostfix, nvisited,sofar,new Point(pos.x+1,pos.y),board);
                recurse(trie, ppostfix, nvisited,sofar,new Point(pos.x-1,pos.y),board);
                recurse(trie, ppostfix, nvisited,sofar,new Point(pos.x,pos.y+1),board);
                recurse(trie, ppostfix, nvisited,sofar,new Point(pos.x,pos.y-1),board);
                return sofar;

            } else {
                return sofar;
            }
        }
    }


}