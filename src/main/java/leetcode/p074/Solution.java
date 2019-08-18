package leetcode.p074;


class Solution {
    public static void main(String[] args){
        int[][] arg = {{1},{2}};
        Solution sol = new Solution();
        sol.searchMatrix(arg,5);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) return false;
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length; //not checking that its truely square n*m
        return recurse_search(0,n*m - 1,target,matrix,n,m);
    }


    int two2oneD(int[][] matrix,int n,int m, int i){
        if (n == 1)
            return matrix[0][i%m];
        else {
            int row = i / m;
            int column = i % m;
            return matrix[row][column];
        }
    }

    boolean recurse_search(int left, int right, int target, int[][] matrix, int n, int m){
        if (left > right) return false;

        int middle = left + (right - left)/2;
        int cur = two2oneD(matrix,n,m,middle);
        if (cur == target) return true;
        else {
            if (cur > target)
                return recurse_search(left,middle -1,target,matrix,n,m);
            else
                return recurse_search(middle + 1, right,target,matrix,n,m);
        }

    }
}