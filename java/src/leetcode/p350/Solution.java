package leetcode.p350;


import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hmap = new HashMap<>();

        for (int i = 0; i < nums1.length; i++){
            int key = nums1[i];
            if (hmap.containsKey(key)){
                int sofar = hmap.get(key);
                hmap.put(key,sofar+1);
            } else {
                hmap.put(key,1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
       for (int i = 0; i < nums2.length; i++){
           int key = nums2[i];
           if (hmap.containsKey(key)){
               int sofar = hmap.get(key);
               if (sofar > 1)
                   hmap.put(key,sofar -1);
               else
                   hmap.remove(key);
               res.add(key);
           }
       }
       int [] ret = new int[res.size()];
       for (int i = 0; i < res.size(); i++)
           ret[i] = res.get(i);
       return ret;
    }
}