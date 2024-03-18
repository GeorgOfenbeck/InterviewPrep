package hackerrank.PlusMinus;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        IntStream stream = Arrays.stream(arr);
        int[] res = stream.mapToObj(x -> {
            int[] t = new int[3];
            if (x > 0)
                t[0] = 1;
            if (x < 0)
                t[1] = 1;
            if (x == 0)
                t[2] = 1;
            return t;
        }).reduce(new int[]{0,0,0},(a,b) -> new int[]{a[0]+b[0],a[1]+b[1],a[2]+b[2]});
        System.out.println(1.0/arr.length*res[0]);
        System.out.println(1.0/arr.length*res[1]);
        System.out.println(1.0/arr.length*res[2]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();
    }
}