package c8.t6;

import java.util.Stack;

public class Hanoi {

    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        Stack<Integer> s3 = new Stack<Integer>();
        for (int i = 0; i< 5; i++){
            s1.push(10-i);
        }
        rebalance(5,s1,s2,s3);
        System.out.println("...");
    }


    public static void rebalance(Integer n, Stack<Integer> source, Stack<Integer> target, Stack<Integer> aux) {
        if (n > 0){
            rebalance(n-1, source, aux, target);
            target.push(source.pop());
            rebalance(n-1,aux,target,source);
        }
    }
}
