package leetcode.p072;

class Solution {

  public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis",
        "ultramicroscopically"));
  }

  public int minDistance(String word1, String word2) {
    if (word1.equals("")) {
      return word2.length();
    }
    if (word2.equals("")) {
      return word1.length();
    }
    int[][] dp = new int[word1.length()][word2.length()];

    if (word1.charAt(0) == word2.charAt(0)) {
      dp[0][0] = 0;
    } else {
      dp[0][0] = 1;
    }

    for (int i = 1; i < word1.length(); i++) {
      if (word1.charAt(i) == word2.charAt(0)) {
        dp[i][0] = Math.min(dp[i - 1][0]+1,i);
      } else {
        dp[i][0] = Math.min(dp[i - 1][0]+1,i+1);
      }
    }

    for (int j = 1; j < word2.length(); j++) {
      if (word1.charAt(0) == word2.charAt(j)) {
        dp[0][j] = Math.min(dp[0][j - 1] + 1,j);
      } else {
        dp[0][j] = Math.min(dp[0][j - 1] + 1,j+1);
      }
    }

    for (int i = 1; i < word1.length(); i++) {
      for (int j = 1; j < word2.length(); j++) {
        if (word1.charAt(i) != word2.charAt(j)) {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
        } else {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j] + 1), dp[i][j - 1] + 1);
          //dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[word1.length() - 1][word2.length() - 1];
  }
}