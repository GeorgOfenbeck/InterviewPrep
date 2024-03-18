package leetcode.p186;

import java.util.*;



class Solution {
    public static void main(String [] args ){
        Solution sol = new Solution();
        char[] s = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        sol.reverseWords(s);
        System.out.println(s);
    }

    private void swap(int i, int j, char[] s){
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    private void reverseSingleWord(int start, int stop, char[] s){
        int len = stop - start;
        int half = len / 2;
        for (int i = 0; i < half; i ++)
            swap(start +i, stop - 1 -i, s);
    }

    public void reverseWords(char[] s) {
        if (s.length == 0) return;
        if (s.length == 1) return;

        for (int i = 0; i < s.length/2; i ++){
            swap(i, s.length-1-i,s);
        }

        int j = 0;
        for (int i = 0; i < s.length; i++){
            if (s[i] == ' ')
            {
                reverseSingleWord(j,i,s);
                j = i + 1;
            }
        }
        if (s[s.length -1] != ' '){
            reverseSingleWord(j,s.length,s);
        }
    }

    public void reverseWords2(char[] s) {
        LinkedList<StringBuffer> ls = new LinkedList<>();

        ls.add(new StringBuffer());
        for (int i = 0; i < s.length; i ++){
            char c = s[i];
            if (c != ' ') {
                StringBuffer sb = ls.getFirst();
                sb.append(c);
            } else {
                StringBuffer sb = new StringBuffer();
                ls.addFirst(sb);
            }
        }
        StringBuffer full = new StringBuffer();
        for (StringBuffer sb : ls){
            full.append(sb);
            full.append(' ');
        }
        char[] x =  full.toString().toCharArray();
        for (int i = 0; i < s.length; i++){
            s[i] = x[i];
        }

    }
}