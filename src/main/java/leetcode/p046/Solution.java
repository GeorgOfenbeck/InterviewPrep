package leetcode.p046;

public class Solution {

    public int minPathSum(int[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;
      int maxtop = 0;
      int maxleft = 0;


      for (int i = 0; i < rows; i++){
        for (int j = 0; j < cols; j++){

          if (i > 0)
            maxtop = grid[i-1][j];
          else
            maxtop = 0;
          if (j > 0)
            maxleft = grid[i-1][j];
          else
            maxleft = 0;

          grid[i][j] = Math.max(maxleft,maxtop);
        }

      }
      return grid[rows-1][cols-1];
    }


}
