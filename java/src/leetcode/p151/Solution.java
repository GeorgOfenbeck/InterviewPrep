package leetcode.p151;

import java.util.*;
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] res = s.split(" ");
        for (int i = res.length-1; i >= 0; i--){
            if (res[i].equals(""))
                continue;
            sb.append(res[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}