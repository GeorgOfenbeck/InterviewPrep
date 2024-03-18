package geeksforgeeks.subarrygsum;

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {


  public static void check(int[] vector, int s) {

    int[][] dp = new int[vector.length + 1][vector.length + 1];

    for (int i = 0; i < vector.length; i++) {
      dp[i][0] = 0;
      dp[0][i] = 0;
    }
    boolean found = false;
    for (int i = 1; i <= vector.length && !found; i++) {
      for (int j = i; j <= vector.length && !found; j++) {
        int sum = dp[i][j - 1] + vector[j - 1];
        dp[i][j] = sum;
        if (sum == s) {
          System.out.println(i + " " + j);
          found = true;
        }
      }
    }
    if (!found) {
      System.out.println("-1");
    }
  }


  public static void check2(int[] vector, int s) {

    boolean found = false;
    int sum = 0;
    int j = 0;
    for (int i = 0; i < vector.length && !found; i++) {
      sum = sum + vector[i];
      if (sum > s) {
        for (; j < i && sum > s && !found; j++) {
          sum = sum - vector[j];
        }
      }
      if (sum == s) {
        System.out.println((j + 1) + " " + (i + 1));
        found = true;
      }
    }

    if (!found)

    {
      System.out.println("-1");
    }

  }


  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int u = 0; u < t; u++) {
      int n = s.nextInt();
      int op = s.nextInt();
      int a[] = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = s.nextInt();
      }

      //System.out.println();
      //check(a, op);
      check2(a, op);

    }
  }
}