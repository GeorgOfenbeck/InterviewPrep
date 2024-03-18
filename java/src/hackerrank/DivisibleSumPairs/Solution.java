package hackerrank.DivisibleSumPairs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Helper {
    List<Integer> smaller;
    List<Integer> larger;
    List<Integer> same;

    Helper() {
        smaller = new LinkedList<>();
        larger = new LinkedList<>();
        same = new LinkedList<>();
    }
}

public class Solution {

    public static BigInteger factorial(BigInteger x) {
        if (x.compareTo(new BigInteger("1")) == 0 || x.compareTo(new BigInteger("0")) == 0)
            return new BigInteger("1");
        else return x.multiply(factorial(x.subtract(new BigInteger("1"))));
    }


    // Complete the divisibleSumPairs function below.
    static int divisibleSumPairs(int n, int k, int[] ar) {
        HashMap<Integer, Helper> hs = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Integer mod = ar[i] % k;
            Integer comp = k - mod;
            Integer key = 0;

            if (mod == 0) {
                key = 0;
            } else {
                if (mod < comp)
                    key = mod;
                else if (mod > comp)
                    key = comp;
                else
                    key = mod;
            }

            hs.putIfAbsent(key, new Helper());
            Helper sofar = hs.get(key);
            if (mod == 0) {
                sofar.same.add(i);
            } else {
                if (mod < comp)
                    sofar.smaller.add(i);
                else if (mod > comp)
                    sofar.larger.add(i);
                else
                    sofar.same.add(i);
            }
        }

        Integer sum = 0;
        Set<Integer> ks = hs.keySet();

        for (Iterator<Integer> kit = ks.iterator(); kit.hasNext(); ) {
            Helper h = hs.get(kit.next());
            if (!h.same.isEmpty()) {
                Integer size = h.same.size();
                BigInteger bs = BigInteger.valueOf(size);
                BigInteger bi = factorial(bs).divide(BigInteger.valueOf(size - 2)).divide(BigInteger.valueOf(2));
                Integer res = bi.intValue();
                sum = sum + res;
            } else {
                Integer res = h.larger.size() * h.smaller.size();
                sum = sum + res;
            }
        }
        ;
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = divisibleSumPairs(n, k, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
