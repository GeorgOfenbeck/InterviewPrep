package leetcode.p014;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) return "";
        if (strs.length == 0) return "";

        StringBuffer sb = new StringBuffer();

        int idxShortest = 0;

        for (int i = 1; i < strs.length; i++)
        {
            if (strs[i].length() < strs[idxShortest].length()) {
                idxShortest = i;
            }
        }
        boolean con = true;
        for (int j = 0; j < strs[idxShortest].length() && con; j++){
            char cur = strs[idxShortest].charAt(j);
            for (int i = 0; i < strs.length && con; i++){
                if (strs[i].charAt(j) != cur)
                    con = false;
            }
            if (con)
                sb.append(cur);
        }
        return sb.toString();
    }
}