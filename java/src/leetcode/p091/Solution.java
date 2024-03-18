package leetcode.p091;

class Solution {

    public static void main(String args[]){
        Solution sol = new Solution();
        sol.numDecodings("226");
    }

    public int numDecodings(String s) {
        /*
        'A' -> 1
'B' -> 2
...
'Z' -> 26
         */

        int[] pos = new int[s.length()];
        if (s.charAt(0) == '0')
            pos[0] = 0;
        else
            pos[0] = 1;
        for (int i = 1; i < s.length(); i ++){
            Character c = s.charAt(i-1);
            Character x = s.charAt(i);
            int pre2 = 0;
            int pre1 = 0;


            pre1 = pos[i-1];


            if (i == 1)
                pre2 = 1;
            else
                pre2 = pos[i-2];

            if (c == '1' || c == '2'){
                if (x == '0')
                    pos[i] = pre2;
                else
                    if (c == '2' && (x == '7' || x == '8' || x == '9'))
                        pos[i] = pre1;
                    else
                        pos[i] = pre1 + pre2;
            } else
                if (x == '0')
                    pos[i] = 0;
                else
                    pos[i] = pre1;

        }
        return pos[s.length()-1];
    }
}