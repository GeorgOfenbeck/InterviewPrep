package leetcode.p008;


class Solution {
    public int myAtoi(String str) {

        boolean whitebefore = true;
        int sign = 1;
        int value = 0;
        for (int i = 0; i < str.length(); i++) {
            Character cur = str.charAt(i);
            if (cur == ' ') {
                if (whitebefore)
                    continue;
                else
                    break;
            } else {
                if (cur == '-' || cur == '+') {
                    if (!whitebefore) break;
                    else if (cur == '-') sign = -1;
                } else {
                    whitebefore = false;
                    if (cur == '1' || cur == '2' || cur == '3' ||
                            cur == '4' || cur == '5' || cur == '6' ||
                            cur == '7' || cur == '8' || cur == '9' || cur == 0) {
                        int curval = (int) cur - (int) '0';
                        value = value * 10 + curval;
                    }
                }
            }

        }
        return sign * value;
    }
}