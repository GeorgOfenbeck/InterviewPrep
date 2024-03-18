package leetcode.p403;
import java.util.*;

class Solution {

  public static void main(String args[] ){
    Solution sol  = new Solution();
    int[] par = {0,2};
    System.out.println(sol.canCross(par));
  }

  public void updateHM(LinkedHashMap<Integer, HashSet<Integer>> hm, Integer stone, Integer k)  {
    if (k <=0) return;
    if (hm.containsKey(stone+k)){
      HashSet<Integer> hs = hm.get(stone+k);
      hs.add(k);
    }
  }

  public boolean canCross(int[] stones) {
    LinkedHashMap<Integer, HashSet<Integer>> hm = new LinkedHashMap<>();
    Arrays.stream(stones).forEach(p -> hm.put(p,new HashSet<>())); // create empty hashset
    HashSet<Integer> hs = new HashSet<>();
    hs.add(0);
    hm.put(0,hs);
    Arrays.stream(stones).forEach( stone -> {
      HashSet<Integer> ks = hm.get(stone);
      ks.stream().forEach(k -> {
        updateHM(hm, stone,k-1);
        updateHM(hm, stone,k);
        updateHM(hm, stone,k+1);
      });
    });
    return !hm.get(stones[stones.length-1]).isEmpty();
  }


}