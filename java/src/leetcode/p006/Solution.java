package leetcode.p006;

import java.util.ArrayList;

class Solution {

    public static void main (String[] args){
        Solution sol = new Solution();
        String in = "PAYPALISHIRING";
        int r = 3;
        sol.convert(in,r);
    }
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        ArrayList<ArrayList<Character>> mat = new ArrayList<>();

        for (int i =0; i < numRows; i++){
            mat.add(i, new ArrayList<>());
        }

        int y = 0;
        int x = 0;
        boolean up = false;
        for (int k = 0; k < s.length(); k++){
            mat.get(y).add( s.charAt(k));
            if (up){
                y--;
                if (y < 0){
                    up = false;
                    y = 1;
                }
            } else {
                y++;
                if (y > mat.size()-1){
                    up = true;
                    y = mat.size()-2;
                }
            }
        }

        StringBuilder full = new StringBuilder();
        for (int i =0; i < numRows; i++){
            for (int j = 0; j < mat.get(i).size(); j++)
                full.append(mat.get(i).get(j));
        }
        return full.toString();

    }
}
