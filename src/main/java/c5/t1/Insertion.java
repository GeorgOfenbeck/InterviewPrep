package c5.t1;

public class Insertion {



    public static void main(String [] args){
        Integer N = 1 << 10;
        Integer M = 0b10011;
        Integer res = Insertion.insert(M,N,2,6);
        System.out.println(res);
    }


    public static Integer insert(Integer M, Integer N, Integer i, Integer j){
        Integer distanced = j - i;

        //do check on positive
        Integer mask = 0;
        for (int k = i; k <= j; k++)
            mask |= 1 << k;

        Integer mm = M << i;
        Integer mn = N & (~mask);
        Integer r = mm | mn;
        return r;
    }

}
