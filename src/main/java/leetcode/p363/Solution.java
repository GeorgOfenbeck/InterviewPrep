package leetcode.p363;

public class Solution {


  public static void main(String[] args) {
    Solution sol = new Solution();
    int[][] matrix = {
        {5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}
    };
    int res1 = sol.maxSumSubmatrix3(matrix, 9);
    int res2 = sol.maxSumSubmatrix2(matrix, 9);
    System.out.println(res1 + " " + res2);
  }



  public int maxSumSubmatrix3(int[][] matrix, int k) {
    if (matrix == null) {
      return 0;
    }
    if (matrix.length == 0) {
      return 0;
    }
    if (matrix[0].length == 0) {
      return 0;
    }
    final int rows = matrix.length;
    final int cols = matrix[0].length;
    int[][] dp = new int[rows + 1][cols + 1];

    //initialize 0 axis to 0

    for (int r = 0; r <= rows; r++) {
      dp[r][0] = 0;
    }

    for (int c = 0; c <= cols; c++) {
      dp[0][c] = 0;
    }

    int maxval = Integer.MIN_VALUE;
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        for (int r = i; r <= rows; r++) {
          for (int c = j; c <= cols; c++) {
            if (i == r && c == j) {
              //System.out.println(r + " " + c);
              dp[r][c] = matrix[i - 1][j - 1];
            } else {
              int left = 0;
              if (c != j)
               left = dp[r][c - 1];
              int top = 0;
              if (r != i)
               top = dp[r - 1][c];
              int diag = 0;
              if (r != i && c != j)
                diag = dp[r - 1][c - 1];
              int sumw = top + left - diag;
              int sum = sumw + matrix[r - 1][c - 1];
              /*if (sum <= 0) {
                dp[i][j][r][c] = matrix[r - 1][c - 1];
              } else {*/
              dp[r][c] = sum;
              //}
            }
            int newmax = Math.max(maxval, dp[r][c]);
            if (newmax <= k) {
              maxval = Math.max(newmax, maxval);
            }

          }
        }
      }
    }
    return maxval;
  }


  public int maxSumSubmatrix2(int[][] matrix, int k) {
    if (matrix == null) {
      return 0;
    }
    if (matrix.length == 0) {
      return 0;
    }
    if (matrix[0].length == 0) {
      return 0;
    }
    final int rows = matrix.length;
    final int cols = matrix[0].length;
    int[][][][] dp = new int[rows + 1][cols + 1][rows + 1][cols + 1];

    //initialize 0 axis to 0
    for (int i = 0; i <= rows; i++) {
      for (int j = 0; j <= cols; j++) {
        for (int r = 0; r <= rows; r++) {
          dp[i][j][r][0] = 0;
        }
      }
    }
    for (int i = 0; i <= rows; i++) {
      for (int j = 0; j <= cols; j++) {
        for (int c = 0; c <= cols; c++) {
          dp[i][j][0][c] = 0;
        }
      }
    }
    for (int i = 0; i <= rows; i++) {
      for (int c = 0; c <= cols; c++) {
        for (int r = 0; r <= rows; r++) {
          dp[i][0][r][c] = 0;
        }
      }
    }
    for (int j = 0; j <= cols; j++) {
      for (int c = 0; c <= cols; c++) {
        for (int r = 0; r <= rows; r++) {
          dp[0][j][r][c] = 0;
        }
      }
    }

    int maxval = Integer.MIN_VALUE;
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        for (int r = i; r <= rows; r++) {
          for (int c = j; c <= cols; c++) {
            if (i == r && c == j) {
              //System.out.println(r + " " + c);
              dp[i][j][r][c] = matrix[i - 1][j - 1];
            } else {
              int left = dp[i][j][r][c - 1];
              int top = dp[i][j][r - 1][c];
              int diag = dp[i][j][r - 1][c - 1];
              int sumw = top + left - diag;
              int sum = sumw + matrix[r - 1][c - 1];
              /*if (sum <= 0) {
                dp[i][j][r][c] = matrix[r - 1][c - 1];
              } else {*/
              dp[i][j][r][c] = sum;
              //}
            }
            int newmax = Math.max(maxval, dp[i][j][r][c]);
            if (newmax <= k) {
              maxval = Math.max(newmax, maxval);
            }

          }
        }
      }
    }
    return maxval;
  }


}
