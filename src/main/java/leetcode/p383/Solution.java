package leetcode.p383;


import java.util.*;

class Solution {
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.canConstruct("aa","ab"));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> hmap = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++){
            Character cur = magazine.charAt(i);
            if (hmap.containsKey(cur)){
                Integer sofar = hmap.get(cur);
                hmap.put(cur,sofar + 1);
            } else
                hmap.put(cur,1);
        }

        boolean stop = false;
        for (int i = 0; !stop && i < ransomNote.length(); i++){
            Character cur = ransomNote.charAt(i);
            if (hmap.containsKey(cur)){
                Integer sofar = hmap.get(cur);
                if (sofar == 1)
                    hmap.remove(cur);
                else
                    hmap.put(cur,sofar - 1);

            } else
                stop = true;
        }
        return !stop;
    }
}