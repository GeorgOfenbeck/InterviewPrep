package leetcode.p779;

/*

On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
Note:

N will be an integer in the range [1, 30].
K will be an integer in the range [1, 2^(N-1)].
 */
class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        //sol.kthGrammar(10, 1);
        System.out.println(sol.kthGrammar(1, 1));
        System.out.println(sol.kthGrammar(2, 1));
        System.out.println(sol.kthGrammar(2, 2));
        System.out.println(sol.kthGrammar(4, 5));
        System.out.println((int) Math.pow(2, 30));
    }

    int rzero = 1;
    int rone = 2;

    public int kthGrammar(int N, int K) {
        //return recurse(0, N, K, 1);
        return closed(0,N,K);
    }

    int closed(int cur, int N, int K) {
        int all = (int) Math.pow(2, N-1); //1,2,4,...
        if (N == 1) return cur;
        if (K <= all / 2) { //left
            if (cur == 0)
                return closed(0, N - 1, K);
            else
                return closed(1, N - 1, K);
        } else {
            if (cur == 0)
                return closed(1, N - 1, K - all/2);
            else
                return closed(0, N - 1, K - all/2);
        }
    }

    int recurse(int sofar, int N, int K, int n) {
        // System.out.println(Integer.toBinaryString(sofar));
        if (N == n) {
            int digits = (int) Math.pow(2, n - 1); //1,2,4
            int pos = 1;
            pos = pos << (digits - K);
            if ((sofar & pos) > 0)
                return 1;
            else
                return 0;
        } else {
            int next = 0;
            for (int i = 0; i < Math.min(Math.pow(2, n), 16); i++) { //2,3,4,... -> 2,4,8,16
                int pos = 1;
                pos = pos << i;
                int cur = sofar & pos;

                int shiftit;
                if (cur > 0) {
                    shiftit = rone;
                } else {
                    shiftit = rzero;
                }
                shiftit = shiftit << (i * 2);
                next = next | shiftit;
            }
            return recurse(next, N, K, n + 1);
        }
    }
}