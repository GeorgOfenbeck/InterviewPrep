package hackerrank.theBirthdayBar;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static int birthday2(List<Integer> s, int d, int m) {
        Iterator<Integer> it = s.iterator();
        LinkedList<Integer> buffer = new LinkedList<Integer>();
        int ways = 0;
        int sum =0;

        int size = 0;
        for (;it.hasNext() && size < m; ){
            size ++;
            Integer value = it.next();
            buffer.addLast(value);
            sum = sum + value;
        }
        if (sum == d) ways ++;

        for (;it.hasNext(); ){
            Integer value = it.next();
            buffer.addLast(value);
            Integer oldvalue = buffer.removeFirst();
            sum = sum + value - oldvalue;
            if (sum == d) ways ++;
        }
        return ways;
    }

    static int birthday(List<Integer> s, int d, int m) {
        //m == length
        //d == sum

        int ways = 0;

        int window = 0;
        int sum = 0;
        if (s.size() < m) return 0;


        for (int i = 0; i < m; i ++){
            sum = sum + s.get(i);
        }
        if (sum == d) ways++;

        for (int i = m; i < s.size(); i++){
            sum = sum + s.get(i) - s.get(i-m);
            if (sum == d) ways ++;
        }
        return ways;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(dm[0]);

        int m = Integer.parseInt(dm[1]);

        int result = birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
