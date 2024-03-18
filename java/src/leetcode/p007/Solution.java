package leetcode.p007;

import java.util.Iterator;
import java.util.*;

class Solution {

    public static void main(String[] args){
        reverse(1534236469);
    }

    public static int reverse(int x) {

        int tmp = 0;
        int a = x;
        boolean sign = true;
        if (a < 0) {
            sign = false;
            a = a * -1;
        }

        ArrayDeque<Integer> digits = new ArrayDeque<>();


        while (a > 0) {
            int digit = a % 10;
            a = a / 10;
            digits.addLast(digit);
        }

        Iterator<Integer> it = digits.iterator();

        int res = 0;

        try{
        while (it.hasNext() && res >= 0) {
            res = Math.multiplyExact(res,10);
            res = Math.addExact(res, it.next());
        }
        } catch(ArithmeticException e){
            res = 0;
        }

        if (sign == false)
            try {
            res = Math.multiplyExact(res , -1);
        } catch (ArithmeticException e){
            res = 0;
            }

        return res;

    }
}
