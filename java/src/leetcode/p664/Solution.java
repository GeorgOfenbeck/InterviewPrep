package leetcode.p664;

import java.util.*;

public class Solution {

  int [][][] dp;

  public static void main(String[]args ){
    String input ="aba";
    Solution sol = new Solution();
    System.out.println(sol.strangePrinter(input));
  }

  public LinkedHashMap<Character, TreeSet<Integer>> positions(String s) {
    LinkedHashMap<Character, TreeSet<Integer>> hm = new LinkedHashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (hm.containsKey(c)){
        TreeSet<Integer> sofar = hm.get(c);
        sofar.add(i);
      } else {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(i);
        hm.put(c,ts);
      }
    }
    return hm;
  }

  public LinkedHashMap<Character, TreeSet<Integer>> deepcopy(LinkedHashMap<Character, TreeSet<Integer>> in){
    LinkedHashMap<Character, TreeSet<Integer>> hm = new LinkedHashMap<>();
    return null;
  }


  public String shorten(String s) {
    StringBuilder sb = new StringBuilder();
    char last = s.charAt(0);
    last = (char) ((int) last + 1);
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c != last) {
        sb.append(c);
      }
      last = c;
    }
    return sb.toString();
  }

  /*
      LinkedHashMap<Character, TreeSet<Integer>> perc = positions(ss);
    TreeSet<Integer> printed = new TreeSet<>();

    perc.entrySet().stream().forEach( (e) -> {
      Character key = e.getKey();
      TreeSet<Integer> value = e.getValue();
    });
    return 0;
   */
  public int strangePrinter(String s) {
    if (s == null) return 0;
    if (s.length() == 0) return 0;
    String ss = shorten(s);
    dp = new int[s.length()][s.length()][2];
    return prnt(s,0,s.length()-1,1,dp);
  }

  public int prnt(String s, int i, int j, int k, int[][][] dp){
    if (j < i) return 0;
    if (i == j) return k;
    if (dp[i][j][k] > 0) return dp[i][j][k];
    else {
      int nowait = k + prnt(s,i+1,j,1,dp);
      int maxwait = Integer.MAX_VALUE;

      for (int m = i+1; m <= j; m++){
        if (s.charAt(i) == s.charAt(m)){
          int tom = prnt(s,i+1,m-1,1,dp) + prnt(s,m,j,k,dp);
          maxwait = Math.min(maxwait,tom);
        }
      }
      int res = Math.min(nowait,maxwait);
      dp[i][j][k] = res;
      return res;
    }
  }

  public int recurse(TreeSet<Integer> printed){
    return 0;
  }
}
