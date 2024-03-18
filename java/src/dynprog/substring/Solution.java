package dynprog.substring;


import java.util.Iterator;
import java.util.*;


class Solution {

    public static void main(String[] args) {
        String a = "foodey";
        String b = "money";

        int res = 0;
        int[][] cache = new int[a.length() + 1][];
        for (int i = 0; i <= a.length(); i++) {
            cache[i] = new int[b.length() + 1];
            for (int j = 0; j <= b.length(); j++)
                cache[i][j] = 0;
        }


        res = longestSubstring(a, b, cache);

        printTable(cache);
        printSolutions(cache, res, a, b);
        System.out.println(res);
    }

    public static void printSolutions(int[][] cache, int cur, String a, String b) {
        print(a, b, cache, cur, a.length(), b.length(), "");
    }

    public static void printTable(int [][] cache){
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++)
                System.out.print(" " + cache[i][j]);

            System.out.println();
        }
    }

    public static void print(String a, String b, int[][] cache, int cur, int x, int y, String sol) {
        if (x == 0 || y == 0)
            return;
        else {
            String ns = sol;
            int top = cache[x - 1][y];
            int left = cache[x][y - 1];

            int me = cache[x][y];

            if (me > Math.max(top, left)) {
                ns = a.charAt(x-1) + sol;
                if (me == 1)
                    System.out.println(ns);
                else
                  print(a, b, cache, cache[x][y], x - 1, y - 1, ns);
            } else {
                if (top == me)
                    print(a, b, cache, cache[x][y], x - 1, y, ns);
                if (left == me)
                    print(a, b, cache, cache[x][y], x, y - 1, ns);
            }
        }


    }


    public static int longestSubString(String a, String b, int[][] cache) {

        Character a0 = a.charAt(0);
        Character b0 = b.charAt(0);

        int al = a.length() - 1;
        int bl = b.length() - 1;
        int res = 0;


        if (cache[al][bl] == -1) {
            if (a.isEmpty() || b.isEmpty())
                return 0;
            if (a0 == b0) {
                res = longestSubString(a.substring(1), b.substring(1), cache) + 1;
                cache[al][bl] = res;
            } else {
                res = Math.max(longestSubString(a.substring(1), b, cache), longestSubString(a, b.substring(1), cache));
                cache[al][bl] = res;
            }
            return res;
        } else return cache[al][bl];
    }

    public static int longestSubstring(String a, String b, int cache[][]) {
        int al = a.length() - 1;
        int bl = b.length() - 1;
        int res = 0;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                Character a0 = a.charAt(i - 1);
                Character b0 = b.charAt(j - 1);

                int max = Math.max(cache[i - 1][j], cache[i][j - 1]);
                if (a0 == b0)
                    cache[i][j] = 1 + cache[i-1][j-1];
                else
                    cache[i][j] = max;
            }
        }

        return cache[a.length()][b.length()];
    }
}