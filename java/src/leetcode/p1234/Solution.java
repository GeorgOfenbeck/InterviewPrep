package leetcode.p1234;

import java.util.*;
class Solution {

    public static void main(String args[]){
        Solution sol = new Solution();
        String  s = "QWER";
        System.out.println(sol.balancedString(s));
    }
    public int balancedString(String s) {
        int qc = 0;
        int wc = 0;
        int ec = 0;
        int rc = 0;

        if (s.length() %4 != 0) return -1;

        for (int k = 0; k < s.length(); k ++){
            char a = s.charAt(k);
            if (a == 'Q') qc++;
            if (a == 'W') wc++;
            if (a == 'E') ec ++;
            if (a == 'R') rc++;
        }

        int target = s.length()/4;

        final int qtarget = qc - target;
        final int wtarget = wc - target;
        final int etarget = ec - target;
        final int rtarget = rc - target;

        if (qtarget == 0 && wtarget == 0 && etarget == 0 && rtarget == 0) return 0;

        qc = 0;
        wc = 0;
        ec = 0;
        rc = 0;

        int minlength = s.length();
        int j = 0;
        for (int i = 0; i < s.length(); i ++){
            char a = s.charAt(i);

            //increase the count
            if (a == 'Q' && qtarget > 0) qc++;
            if (a == 'W' && wtarget > 0) wc++;
            if (a == 'E' && etarget > 0) ec ++;
            if (a == 'R' && rtarget > 0) rc++;

            if (qc >= qtarget  && wc >= wtarget && rc >= rtarget && ec >= etarget ) //we have all chars
            {
                while (qc >= qtarget  && wc >= wtarget && rc >= rtarget && ec >= etarget)
                {
                    int length = i - j + 1;
                    if (length < minlength) minlength = length;
                    char remove = s.charAt(j);
                    if (remove == 'Q' && qtarget > 0) qc--;
                    if (remove == 'W' && wtarget > 0) wc--;
                    if (remove == 'E' && etarget > 0) ec--;
                    if (remove == 'R' && rtarget > 0) rc--;
                    j ++;
                }
            }
        }
        return minlength;
    }
}