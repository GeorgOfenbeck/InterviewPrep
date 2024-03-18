package leetcode.p003;

import java.util.HashMap;

class Solution {

    public static void main(String [] args){
        Solution sol = new Solution();
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> hmap = new HashMap<>();
        int maxLength = 0;
        int curLength = 0;
        int cur_cutoff = 0;
        for (int i = 0; i < s.length(); i++){
            Character cur = s.charAt(i);
            if (hmap.containsKey(cur)){
                Integer posOld = hmap.get(cur);
                if (posOld < cur_cutoff)
                {
                    curLength++;
                    if (curLength > maxLength)
                        maxLength = curLength;
                    hmap.put(cur,i);
                } else {
                    curLength = i - posOld;
                    hmap.put(cur, i);
                    cur_cutoff = posOld;
                    if (curLength > maxLength)
                        maxLength = curLength;
                }
            } else {
                curLength++;
                if (curLength > maxLength)
                    maxLength = curLength;
                hmap.put(cur,i);
            }
        }

        return maxLength;
    }
}