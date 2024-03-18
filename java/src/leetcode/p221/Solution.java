package leetcode.p221;

class Solution {
    
    public static void main(String[] args){
        char [][] matrix = 
                /*
                {
                {'1','0','1','0','0','1','1','1','0'},
                {'1','1','1','0','0','0','0','0','1'},
                {'0','0','1','1','0','0','0','1','1'},
                {'0','1','1','0','0','1','0','0','1'},
                {'1','1','0','1','1','0','0','1','0'},
                {'0','1','1','1','1','1','1','0','1'},
                {'1','0','1','1','1','0','0','1','0'},
                {'1','1','1','0','1','0','0','0','1'},
                {'0','1','1','1','1','0','0','1','0'},
                {'1','0','0','1','1','1','0','0','0'}};*/
        
        {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        Solution sol = new Solution();
        System.out.println(sol.maximalSquare(matrix));
    }
    
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) return 0;
        if (matrix.length == 0) return 0;

        int res = 0;

        int[][] buffer = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++){
            buffer[i] = new int[matrix[0].length];
        }
        int tmp;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++){
                tmp = getGreatest(matrix,buffer,i,j);
                res = Math.max(tmp,res);
            }


        return res*res;
    }

    private int getGreatest(char [][] matrix, int[][] buffer, int x, int y){
        if (matrix[x][y] == '0') {
            buffer[x][y] = 0;
            return 0;
        } else
            if (x > 0 && y > 0 && matrix[x-1][y-1] == '0'){
                buffer[x][y] = 1;
                return 1;
            }
            else
            {
            if (x == 0 && y == 0) {
                if (matrix[x][y] == '0') {
                    buffer[x][y] = 0;
                    return 0;
                } else {
                    buffer[x][y] = 1;
                    return 1;
                }
            }
            int left = 0;
            if (y == 0) left = 0; else {
                left = buffer[x][y-1];
            }

            int up = 0;
            if (x == 0) up = 0; else {
                up = buffer[x-1][y];
            }

            int diag = 0;
            if (x > 0 && y > 0){
              diag = buffer[x-1][y-1];
            }


            int res = Math.min(Math.min(left,up),diag);
            buffer[x][y] = res+1;
            return res + 1;
        }
    }
}
