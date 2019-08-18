package leetcode.p240;

class Solution {
    public static void main(String[] args){
        int[][] arg = {{1,3,5}};//{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        Solution sol = new Solution();
        boolean res = sol.searchMatrix(arg,-1);
        System.out.println(res);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) return false;
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length; //not checking that its truely square n*m
        //return recurse_search(0,n*m - 1,target,matrix,n,m);

        return threequartier(matrix,0,0,n-1,m-1,target);
    }


    boolean threequartier(int [][]matrix, int sx, int sy, int ex, int ey,int target){
        if (sx > ex || sy > ey || sx < 0 || sy < 0 || ex >= matrix.length || ey >= matrix[0].length) return false;

        int xsize = ex - sx;
        int ysize = ey - sy;

        if (xsize == 0 && ysize == 0)
            if (matrix[sx][sy] == target) return true;
            else return false;
        if (xsize == 1 && ysize == 1){
            if (matrix[sx][sy] == target || matrix[sx+1][sy] == target || matrix[sx][sy+1] == target || matrix[sx+1][sy+1] == target) return true;
            else return false;
        }
        if (xsize == 1 && ysize == 0)
            if (matrix[sx][sy] == target || matrix[sx+1][sy] == target) return true;
            else return false;
        if (xsize == 0 && ysize == 1)
            if (matrix[sx][sy] == target || matrix[sx][sy+1] == target) return true;
            else return false;

        int midx = sx + (ex - sx)/2;
        int midy = sy + (ey - sy)/2;

        int cur = matrix[midx][midy];
        if (cur == target)return true;

        if (cur > target){
            return (threequartier(matrix,sx,sy,midx,midy,target) || threequartier(matrix,midx + 1,sy,ex,midy-1,target)  ||
                    threequartier(matrix,sx,midy +1,midx-1,ey,target) );
        } else {
            return (threequartier(matrix,midx,midy,ex,ey,target) || threequartier(matrix,midx + 1,sy,ex,midy-1,target)  ||
                    threequartier(matrix,sx,midy +1,midx-1,ey,target) );
        }
    }




}