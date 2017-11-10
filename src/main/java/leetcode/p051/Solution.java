package leetcode.p051;
import java.util.Iterator;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.totalNQueens(4 );
        System.out.println(res);
    }

    int total = 0;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> llist = new ArrayList<>();

        boolean field[][]  = new boolean[n][n];

        for (int i = 0; i < n; i++){
            for (int k = 0; k < n; k++){
                field[i][k] = true;
            }
        }

        return null;

    }
    public int totalNQueens(int n) {
        BitSet[] field = new BitSet[n];
        for (int i = 0; i < n; i++)
            field[i] = new BitSet(n);

        total = 0;
        dfs(field,0,n);
        return total;
    }

    private void dfs(BitSet[] field, int row, int n){

        if (row == n-1)
        {
            for (int i = 0; i < n; i++){
                if (!field[row].get(i)){
                    total++;
                }
            }

        } else {
            for (int i = 0; i < n; i++){
                if (!field[row].get(i)){
                    BitSet[] newsolution = update(field,row,i,n);
                    dfs(newsolution,row+1,n);
                }
            }
        }
    }


    private BitSet[] update(BitSet[] field, int row, int col, int n){
        BitSet myrow = field[row];

        BitSet[] copy = new BitSet[n];
        for (int i = 0; i < n; i++) {
            copy[i] = new BitSet(n);
            copy[i].or(field[i]);
        }

        for (int i = row+1; i < n; i++){
            BitSet newrow = new BitSet(n);
            newrow.clear();
            newrow.set(col, true);
            int left = col - (i - row);
            int right = col + (i - row);
            if (left >= 0)
                newrow.set(left, true);
            if (right < n)
                newrow.set(right, true);
            copy[i].or(newrow);
        }
        return copy;
    }
}