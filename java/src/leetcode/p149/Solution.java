package leetcode.p149;

public class Solution {

    class Trin{
        int up;
        int left;
        int diag;
        Trin(int up, int left, int diag){
            this.up = up;
            this.left = left;
            this.diag = diag;
        }
        int max() {
                return Math.max(Math.max(up,left),diag);
        }
    }

    public int maxPoints(int[][] points) {
        if (points == null) return 0;
        if (points.length == 0) return 0;
        if (points[0] == null) return 0;
        if (points[0].length == 0) return 0;

        int xsize = points.length;
        int ysize = points[0].length;

        Trin[] prev = new Trin[ysize];
        Trin[] curr = new Trin[ysize];

        for (int i= 0; i< ysize; i++){
            prev[i] = new Trin(0,0,0);
        }

        int max = 0;
        for (int i = 0; i < xsize; i++){
            for (int j = 0; j < ysize; j++){
                if (points[i][j]== 0){
                    curr[j] = new Trin(0,0,0);
                } else {

                }
            }
        }

        return -1;
    }
}
