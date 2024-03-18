package leetcode.p903;
/*
We are given S, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)

A valid permutation is a permutation P[0], P[1], ..., P[n] of integers {0, 1, ..., n}, such that for all i:

If S[i] == 'D', then P[i] > P[i+1], and;
If S[i] == 'I', then P[i] < P[i+1].
How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.



Example 1:

Input: "DID"
Output: 5
Explanation:
The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)


Note:

1 <= S.length <= 200
S consists only of characters from the set {'D', 'I'}.

 */

import java.util.*;

class Entry {
    int smaller;
    int larger;
    String seq;

    Entry(int smaller, int larger, String seq) {
        this.smaller = smaller;
        this.larger = larger;
        this.seq = seq;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Entry other = (Entry) obj;
            if (other.larger == larger && other.smaller == smaller) return true;
            else return false;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(smaller) + Integer.hashCode(larger);
    }
}


class Solution {
    private static final int DIV = 1000000007;
    //HashMap<Entry, Long> hmap = new HashMap<>();
    int[][] cache;
    public static void main(String[] args) {
        Solution sol = new Solution();
        long num = sol.numPermsDISequence("IIDDIDDIIDDIDIDDIDDDDIIIDIDIDDDDDIIDIDDIDIIDIDDIIIDIIIIIIIIDIDIDIDDIDIIIIDDIIIIDDDDIIIDDIIDDIDIIIDDDDDDIIDIDDIIDDDDIIDDDIDIDDDIIIDIDIDIIIDDIDDDDDDDDIIDDIDDDIDDDIDDDDIIDIIIDDIDDDIDDIDDDIIDDIIIDIDIIDIDI");
        System.out.println(num);

        //println num ; //% (10E9+7);
    }

    public int numPermsDISequence(String S) {
        int n = S.length();
        cache = new int[n+2][n+2];
        Entry entry = new Entry(n+1,0,"D" + S);
        long premod =rec(entry);
       // System.out.println(premod);
        long mod = (premod% DIV)  ;
        return (int) mod;
    }


    long rec(Entry state) {
        /*if (hmap.containsKey(state))
            return hmap.get(state);
         */
        if (cache[state.larger][state.smaller] > 0)
            return cache[state.larger][state.smaller] - 1;
        else {
            long result = 0;
            String seq = state.seq;
            if (seq.isEmpty()) result = 1;
            else {
                Character head = seq.charAt(0);
                String rem = seq.substring(1, seq.length());
                if (head == 'I') {
                    if (state.larger == 0)
                        result = 0;
                    else {
                        for (int i = 0; i < state.larger; i++) {
                            Entry next = new Entry(state.smaller + i, state.larger - 1 - i, rem);
                            result = result + rec(next);
                        }
                    }
                } else {
                    if (state.smaller == 0)
                        result = 0;
                    else {
                        for (int i = 0; i < state.smaller; i++) {
                            Entry next = new Entry(state.smaller - 1 - i, state.larger + i, rem);
                            result = result + rec(next);
                        }
                    }
                }
            }
            long mod = (result% DIV)  ;
            //hmap.put(state,mod);
            cache[state.larger][state.smaller] = (int) mod + 1;
            return mod;
        }
    }


}