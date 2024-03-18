package leetcode.p233;

import java.util.HashMap;

/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.


//if leading is 1

0-9 = 1
10-19 = 10 + 1
20 - 29 = 1
30 ... = 1
100 - 109 = 10 + 1
110 - 119 = 10 + 10 + 1
120 - 129 = 10 + 0 + 1

 */
class Solution {

    HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

    public static void main(String args[]) {
        Solution sol = new Solution();
        int i = 1235;
        System.out.println(sol.countDigitOneBruteForce(i));
        System.out.println(sol.splitTenPowers(i));
    }

    public int countDigitOne(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n < 10) return 1;
        //return nrOnes(n,10, 1);
        return 0;
    }

    public int splitTenPowers(int n) {
        int rest = n;
        int sum = 0;
        int pos = 0;
        while (rest >= 1) {
            int digit = rest % 10;
            rest = rest / 10;
            int res = formula(digit, pos, n);
            sum +=  res;
            pos++;
        }

        return sum;
    }

    public int formula(int digit, int power, int n) {
        if (digit == 0) return 0;
        if (power == 0 && digit == 10) return 1;
        if (power < 0) return 0;
        if (digit == 1)
        {
            //smaller then me
            int pow = (int) Math.pow(10, power);
            int rest = n % pow;
            int rec = digit * formula(10, power - 1,n);
            return rest +1  + rec;
        } else {
            int pow = (int) Math.pow(10, power);
            int rec = digit * formula(10, power - 1,n);
            return pow + rec;
        }
    }


    public int nrDigitsFull(int n) {
        int count = 0;
        for (int i = n; i >= 1; i = i / 10) {
            int rem = i % 10;
            if (rem == 1) count++;
        }
        return count;
    }


    public int countDigitOneBruteForce(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count = count + nrDigitsFull(i);
        }
        return count;
    }


}