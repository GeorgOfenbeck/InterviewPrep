package leetcode.p097;

import java.util.Arrays;

public class Solution {

  
  class Node{
    Node next= null;
    Integer index = 0;
    char c;
    Node(char pc, int pindex)
    {
      c = pc;
      index = pindex;
    }
    
  }


  public static void main(String[] args){
    Solution sol = new Solution();
    String a = "a";
    String b = "b";
    String c = "ab";
   /* String a = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
    String b = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
    String c = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";

*/    sol.isInterleave(a,b,c);


  }

  public Node toList(String s1){
    Node dummy = new Node('d',99);
    dummy.next = null;
    for (int i = 0; i < s1.length(); i++){
      Node x = new Node(s1.charAt(i),i);
      x.next = dummy.next;
      dummy.next = x;
    }
    return dummy.next;
  }
  
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) return false;
    if (s3.isEmpty() && s2.isEmpty() && s1.isEmpty()) {
      return true;
    }
    if (s3.isEmpty()) {
      return false;
    }

    if (s1.isEmpty()) {
      return s2.equals(s3);
    }
    if (s2.isEmpty()) {
      return s3.equals(s1);
    }
    int cache[][] = new int[s1.length() + 1][s2.length() + 1];
    
    Node al = toList(s1);
    Node bl = toList(s2);
    Node cl = toList(s3);
    //boolean res =  isInterleave2(s1,s2,s3, cache);
    boolean res2 =  isInterleave3(al, bl, cl, cache);
    return res2;
  }


  public boolean nodeEqual(Node a, Node b){
    if (a == null && b == null) return true;
    if (a == null) return false;
    if (b == null) return false;

    if (a.c == b.c) return nodeEqual(a.next,b.next);
    else return false;
  }


  public boolean isInterleave3(Node s1, Node s2, Node s3, int cache[][]) {

    if (s3 == null && s2 == null && s1 == null) {
      return true;
    }
    if (s3 == null) {
      cache[s1.index][s2.index] = -1;
      return false;
    }

    if (s1 == null) {
      return nodeEqual(s2,s3);
    }
    if (s2 == null) {
      return nodeEqual(s1,s3);
    }

    if (cache[s1.index][s2.index] != 0) {
      //System.out.println("Cache hit");
      if (cache[s1.index][s2.index] == 1) {
        return true;
      } else {
        return false;
      }
    }
    Character a = s1.c;
    Character b = s2.c;
    Character c = s3.c;

    boolean left = false;
    boolean right = false;
    if (c != a && c != b) {
      cache[s1.index][s2.index] = -1;
      return false;
    }

    if (a == c) {
      left = isInterleave3(s1.next, s2, s3.next, cache);
    }
    if (!left && b == c) {
      right = isInterleave3(s1, s2.next, s3.next, cache);
    }

    boolean res = left || right;
    if (res)
      cache[s1.index][s2.index] = 1;
    else
      cache[s1.index][s2.index] = -1;
    return res;

  }

  public boolean isInterleave2(String s1, String s2, String s3, int cache[][]) {
    if (cache[s1.length()][s2.length()] != 0) {
      //System.out.println("Cache hit");
      if (cache[s1.length()][s2.length()] == 1) {
        return true;
      } else {
        return false;
      }
    }
    if (s3.isEmpty() && s2.isEmpty() && s1.isEmpty()) {
      cache[s1.length()][s2.length()] = 1;
      return true;
    }
    if (s3.isEmpty()) {
      cache[s1.length()][s2.length()] = -1;
      return false;
    }

    if (s1.isEmpty()) {
      if (!s2.equals(s3)) {
        cache[s1.length()][s2.length()] = -1;
      }
      else
        cache[s1.length()][s2.length()] = 1;
      return s2.equals(s3);
    }
    if (s2.isEmpty()) {
      if (!s3.equals(s1)) {
        cache[s1.length()][s2.length()] = -1;
      } else
        cache[s1.length()][s2.length()] = 1;
      return s3.equals(s1);
    }

    Character a = s1.charAt(0);
    Character b = s2.charAt(0);
    Character c = s3.charAt(0);

    boolean left = false;
    boolean right = false;
    if (c != a && c != b) {
      cache[s1.length()][s2.length()] = -1;
      return false;
    }

    if (a == c) {
      left = isInterleave2(s1.substring(1), s2, s3.substring(1), cache);
    }
    if (!left && b == c) {
      right = isInterleave2(s1, s2.substring(1), s3.substring(1), cache);
    }

    boolean res = left || right;
    if (res)
      cache[s1.length()][s2.length()] = 1;
    else
      cache[s1.length()][s2.length()] = -1;
    return res;

  }
}
