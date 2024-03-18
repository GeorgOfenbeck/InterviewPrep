package leetcode.p546;

public class Solution {

  public static void main (String [] args){
    int[] par = {1, 3, 2, 2, 2, 3, 4, 3, 1};
    Solution sol = new Solution();
    System.out.println(sol.removeBoxes(par));
  }


  public int removeBoxes(int[] boxes) {
    int n = boxes.length;
    int[][][] dp = new int[n][n][n];
    return removeBoxesSub(boxes, 0, n - 1, 0, dp);
  }

  private int removeBoxesSub(int[] boxes, int i, int j, int k, int[][][] dp) {
    if (i > j) return 0;
    if (dp[i][j][k] > 0) return dp[i][j][k];
    else {
     int onlyfirst = (k+1)*(k+1) + removeBoxesSub(boxes,i+1,j,0,dp);
     int maxconcat = 0;
     for (int m = i+1; m <= j; m++){
       if (boxes[m] == boxes[i]) {
         int concatnext =
             removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp);
         maxconcat = Math.max(maxconcat, concatnext);
       }
     }
     int res = Math.max(onlyfirst,maxconcat);
      dp[i][j][k] = res;
     return res;
    }
  }
}
