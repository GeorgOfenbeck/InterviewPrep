package leetcode.p139;
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
Accepted

 */

import java.util.*;

class Solution {

    int[] cache;


    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++){
            set.add(wordDict.get(i));
        }
        cache = new int[s.length()];
        return canSplit(0,s.length(),s,set);
    }


    boolean canSplit(int start, int end, String s, HashSet<String> dict) {
        if (start == end) return true;

        if (cache[start] != 0){
            if (cache[start] == -1) return false;
            else return true;
        }
        for (int i = start; i <= end; i++){ //n times -> O(n^2)
            if (hasMatch(start,i,s,dict) && canSplit(i,end,s,dict)) {
                cache[start] = 1;
                return true;
            }
        }
        cache[start] = -1;
        return false;
    }

    boolean hasMatch(int start, int end, String s, HashSet<String> dict){
        String sub = s.substring(start,end); //O(n)
        return dict.contains(sub); //O(1)
    }
}