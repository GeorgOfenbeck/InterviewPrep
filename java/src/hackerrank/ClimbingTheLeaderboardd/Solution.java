package hackerrank.ClimbingTheLeaderboardd;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;




public class Solution {






    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {

        int [] result = new int[alice.length];

       TreeMap<Integer, Integer> tset = new TreeMap<>();
        for (int i = 0; i < scores.length; i++)
            tset.put(scores[i], 0);


        //System.out.println("number of total entries " + tset.size());
        for (int i = 0; i < alice.length; i++) {
            int current = alice[i];
//            System.out.println("Inserting Alice score " + current);
            tset.put(current,0);
            NavigableMap head = tset.headMap(current, false); //smaller set

//            System.out.println("Headset size " + head.size());
            int totalEntries = tset.size();
            result[i] = totalEntries - head.size();
//            System.out.println("Total Entries " + totalEntries + " , result[i]: " + result[i]);
        }



        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /*
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        */
        int[] scores = {100, 90, 90, 80, 75, 60};
        int[] alice = {50, 65, 77, 90, 102};
        int[] result = climbingLeaderboard(scores, alice);

        System.out.println("Results:");
        for (int i = 0; i < result.length; i ++){
            System.out.println(result[i]);
        }
        /*
        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();

         */
    }
}
