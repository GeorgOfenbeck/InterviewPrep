package leetcode.p032;

import java.util.*;

public class Solution {

  public int longestValidParentheses(String s) {
    Stack<Integer> parant = new Stack<>();
    int []dp = new int[s.length()+1];

    int max = 0;
    for (int i = 0; i < s.length(); i ++){
      Character c = s.charAt(i);

      if (c == '(')
        parant.push(i);
      else {
        if (!parant.isEmpty()) {
          Integer start = parant.pop();
          Integer beforesize = dp[start];;
          Integer size = beforesize + (i - start) + 1;
          dp[i+1] = size;
          if (size > max)
            max = size;
        }
      }
    }
    return max;
  }
}
