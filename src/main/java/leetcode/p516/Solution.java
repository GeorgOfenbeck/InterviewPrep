package leetcode.p516;

import java.util.BitSet;
import java.util.HashMap;

class Solution {

  static public void main(String[] arg) {
    Solution sol = new Solution();
    String s = "bbbab";
    System.out.println(sol.longestPalindromeSubseq(s));
  }


  int[][] cache;

  public int longestPalindromeSubseq(String s) {
    char[] sa = s.toCharArray();
    Boolean[][] matrix = new Boolean[sa.length][sa.length];

    cache = new int[sa.length][sa.length];
    for (int i = 0; i < sa.length; i++) {
      if (s == null) {
        return 0;
      }
    }
    if (s.length() == 1) {
      return 1;
    }
    int max = 0;

    for (int i = 0; i < sa.length; i++) {
      for (int jj = 0; jj < sa.length; jj++) {
        cache[i][jj] = -1;
      }
    }

    for (int i = 0; i < sa.length; i++) {
      //max = Math.max(checkPalindrom2(i - 1, i, sa, 0), max);
      //max = Math.max(checkPalindrom2(i - 1, i + 1, sa, 1), max);
      //max = Math.max(checkPalindrom2(i-1, i, sa, 0), max); // center case
      max = Math.max(checkPalindrom2(i, i, sa, 0), max); //non center case
      max = Math.max(checkPalindrom2(i - 1, i, sa, 0), max); //non center case
      //System.out.println("neu start");
    }
    return max;
  }

  public int checkPalindrom2(int left, int right, char[] sa, int sofar) {
    int wert = 0;
    if (left < 0 || right >= sa.length) {
      return sofar;
    } else {
      //if (cache[left][right] > 0) return cache[left][right];
      /*
      System.out.print("links:" );
      System.out.print(left);
      System.out.print(sa[left]);
      System.out.print(" rechts:" );
      System.out.print(right);
      System.out.print(sa[right]);
      System.out.println();
      */
      if (sa[left] == sa[right]) {
        if (left == right) {
          if (cache[left][right] >= 99) {
            wert = cache[left][right];
          } else {
            wert = checkPalindrom2(left - 1, right + 1, sa, sofar);
          }
          cache[left][right] = wert + 1;
          return wert;
        } else {
          if (cache[left][right] >= 99) {
            return cache[left][right];
          } else {
            wert = checkPalindrom2(left - 1, right + 1, sa, sofar);
          }
          cache[left][right] = wert + 2;
          return wert + 1;
        }
      } else {
        wert = Math.max(checkPalindrom2(left - 1, right, sa, sofar),
            checkPalindrom2(left, right + 1, sa, sofar));
        cache[left][right] = wert;
        return wert;
      }
    }
  }


  public int longestPalindromeSubseq2(String s) {
    char[] sa = s.toCharArray();
    Boolean[][] matrix = new Boolean[sa.length][sa.length];
    for (int i = 0; i < sa.length; i++)

    {
      if (s == null) {
        return 0;
      }
    }
    if (s.length() == 1) {
      return 1;
    }
    int max = 0;
    for (int i = 1; i < sa.length; i++) {
      max = Math.max(checkPalindrom(i - 1, i, sa, 0), max);
      max = Math.max(checkPalindrom(i - 1, i + 1, sa, 1), max);
    }
    return max;
  }


  public int checkPalindrom(int left, int right, char[] sa, int sofar) {
    if (left < 0 || right >= sa.length) {
      return sofar;
    }
    if (sa[left] == sa[right]) {
      return checkPalindrom(left - 1, right + 1, sa, sofar + 2);
    } else {
      return sofar;
    }
  }


  public int longestPalindromeSubseq3(String s) {
    char[] sa = s.toCharArray();
    int[][] matrix = new int[sa.length][sa.length];
    int[][] matrix2 = new int[sa.length][sa.length];

    for (int i = 0; i < sa.length; i++) {
      if (sa[0] == sa[i]) {
        matrix[0][i] = 1;
        matrix[i][0] = 1;
        matrix2[0][i] = 1;
        matrix2[i][0] = 1;
      }
    }
    int maxlength = 0;

    for (int j = 1; j < sa.length; j++) { //slow running
      for (int i = sa.length - 2; i >= 0; i--) {
        if (sa[i] == sa[j]) {
          matrix[i][j] = 1;
        }
        matrix2[i][j] = matrix2[i + 1][j - 1] + Math.max(matrix[i][j - 1], matrix[i][j]);
        maxlength = Math.max(maxlength, matrix2[i][j]);
      }
    }
 /*
    for (int i = 0 ; i< sa.length; i++) {
      for (int j = 0; j < sa.length; j++) {
        System.out.print(matrix[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }

    System.out.println(); System.out.println();
    for (int i = 0 ; i< sa.length; i++) {
      for (int j = 0; j < sa.length; j++) {
        System.out.print(matrix2[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
    */
    return maxlength;
  }

}
