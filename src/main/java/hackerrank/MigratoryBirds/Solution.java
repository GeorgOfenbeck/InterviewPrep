package hackerrank.MigratoryBirds;
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

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        HashMap<Integer,Integer> hmap = new HashMap<>();

        int curmax = -1;
        int curid = -1;
        for (int i = 0; i < arr.size(); i++){
            Integer ele = arr.get(i);
            int count = -1;
            if (hmap.containsKey(ele)){
                Integer sofar = hmap.get(ele);
                count = sofar + 1;
                hmap.put(ele,count);
            } else {
                count = 1;
                hmap.put(ele,count);
            }
            if (count > curmax){
                curmax = count;
                curid = ele;
            } else {
                if (curmax == count){
                    if (curid > ele)
                        curid = ele;
                }
            }
        }
        return curid;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
