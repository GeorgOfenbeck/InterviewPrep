package leetcode.p312;

public class Solution {

  int[][][][] dp;
  int[][] dp2;


  public static void main(String [] args){
    Solution sol = new Solution();
    //int[] par = {9,76,64,21};
    int[] par = {3, 1, 5, 8};
    System.out.println(sol.maxCoins(par));
  }

  public int maxCoins(int[] nums) {
    if (nums == null) return 0;
    if (nums.length == 0) return 0;
    //dp = new int[nums.length][nums.length][nums.length+2][nums.length+2];
    //return rec(nums,0,nums.length-1,-1,nums.length,dp);
    dp2 = new int[nums.length+2][nums.length+2];
    return rec2(nums,0,nums.length-1,-1,nums.length,dp2);
  }


  public int rec2(int[] nums, int i, int j, int l, int r, int[][] dp){
    if (j < i) return 0;
    if (dp[l+1][r+1] > 0) return dp[l+1][r+1];
    if (i == j) {
      int left = 0;
      int right = 0;
      if (l == -1) left = 1; else left = nums[l];
      if (r == nums.length) right = 1; else right = nums[r];
      int res = left * right * nums[i];
      dp[l+1][r+1] = res;
      return res;
    } else {
      int pop = rec2(nums,i,i,l,i+1,dp) + rec2(nums,i+1,j,l,j+1,dp);

      int maxnonpop = 0;
      for (int m = i+1; m <= j; m++ ){
        int mpop = rec2(nums,i+1,m,i,m+1,dp) + rec2(nums,i,i,l,m+1,dp) + rec2(nums,m+1,j,l,j+1,dp);
        maxnonpop = Math.max(mpop,maxnonpop);
      }
      int res = Math.max(maxnonpop,pop);
      dp[l+1][r+1] = res;
      return res;
    }
  }

  public int rec(int[] nums, int i, int j, int l, int r, int[][][][] dp){
    if (j < i) return 0;
    if (dp[i][j][l+1][r+1] > 0) return dp[i][j][l+1][r+1];
    if (i == j) {
      int left = 0;
      int right = 0;
      if (l == -1) left = 1; else left = nums[l];
      if (r == nums.length) right = 1; else right = nums[r];
      int res = left * right * nums[i];
      dp[i][j][l+1][r+1] = res;
      return res;
    } else {
      int pop = rec(nums,i,i,l,i+1,dp) + rec(nums,i+1,j,l,j+1,dp);

      int maxnonpop = 0;
      for (int m = i+1; m <= j; m++ ){
        int mpop = rec(nums,i+1,m,i,m+1,dp) + rec(nums,i,i,l,m+1,dp) + rec(nums,m+1,j,l,j+1,dp);
        maxnonpop = Math.max(mpop,maxnonpop);
      }
      int res = Math.max(maxnonpop,pop);
      dp[i][j][l+1][r+1] = res;
      return res;
    }
  }
}
