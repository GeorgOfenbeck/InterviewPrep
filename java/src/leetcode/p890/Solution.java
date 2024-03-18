package leetcode.p890;

import java.util.*;

class Solution {

    public static void main (String[] args){
        String[] test = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        Solution sol = new Solution();


        String [] split = "aa  b cc".split(" ");
        sol.findAndReplacePattern(test,pattern);
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        Integer ipattern = word2int(pattern);
        List<Integer> ilist = words2ints(words);

        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < ilist.size(); i++){
            if (ilist.get(i).equals( ipattern))
                res.add(words[i]);
        }
        return res;
    }


    List<Integer> words2ints(String[] words){
        ArrayList<Integer> ilist = new ArrayList<>();
        for (int i = 0; i< words.length; i++){
            ilist.add(word2int(words[i]));
        }
        return ilist;
    }

    Integer word2int(String word){
        HashMap<Character, Integer> hmap = new HashMap<>();
        int count = 1;
        int res = 0;
        for (int i = 0; i < word.length(); i ++){
            Character cur = word.charAt(i);

            if(hmap.containsKey(cur)){
                res = res * 50 + hmap.get(cur);
            } else {
                hmap.put(cur,count);
                res = res * 50 + count;
                count ++;
            }
        }
        return res;
    }


}