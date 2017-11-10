package leetcode.p062;

import java.util.Iterator;
import java.util.*;


class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.uniquePaths(30, 30);
        System.out.println(res);
    }

    private int solution = 0;
    private int[][] cache = null;


    public int uniquePaths(int m, int n) {
        solution = 0;
        cache = new int[m][n];
        return dynmove(m - 1, n - 1);
    }

    private int dynmove(int m, int n) {
        if (m == 0 && n == 0)
            return 1;
        else {
            if (cache[m][n] != 0)
                return cache[m][n];
            else {
                int top = 0;
                int left = 0;
                if (m > 0)
                    if (cache[m - 1][n] != 0)
                        top = cache[m - 1][n];
                    else
                        top = dynmove(m - 1, n);
                if (n > 0)
                    if (cache[m][n - 1] != 0)
                        left = cache[m][n - 1];
                    else
                        left = dynmove(m, n - 1);
                cache[m][n] = top + left;
                return top + left;
            }
        }
    }

    private void move(int m, int n, int posx, int posy) {
        if (m == posx && n == posy) {
            solution++;
        } else if (m == posx)
            move(m, n, posx, posy + 1);
        else if (n == posy)
            move(m, n, posx + 1, posy);
        else {
            move(m, n, posx, posy + 1);
            move(m, n, posx + 1, posy);
        }
    }


}
