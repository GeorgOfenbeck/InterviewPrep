package leetcode.p1017;

class Solution {
    public static void main(String[] args){
        for (int i = 0; i < 128; i ++){
            StringBuilder sb = new StringBuilder();
            int cur = i;
            int number = 0;
            int sign = 1;
            int pow = 0;
            while (cur > 0){
                if (cur % 2 == 1){
                    number = number + (int)Math.pow(-2,pow);
                    sb.append('1');
                }
                else {
                    sb.append('0');
                }
                sign = sign * -1;
                cur = cur / 2;
                pow = pow + 1;
            }
            sb.reverse();
            System.out.println(sb.toString() + " -> " + number);

        }
        int tmp = 5 % -3;
        System.out.println( tmp);
    }



    public String baseNeg2(int N) {
        if (N == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            int r = N % (-2);
            N = N / (-2);
            if (r < 0) {
                r += 2;
                N += 1;
            }
            sb.append(r);
        }
        return sb.reverse().toString();


    }
}