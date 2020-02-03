package hackerrank.TimeConversion;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {



    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        //hh:mm:ssAM
        if (s == null) return null;
        if (s.length() != 10) return null;

        String hours = s.substring(0,2);
        Integer inth = Integer.parseInt(hours);
        String pm = s.substring(s.length()-2);
        if (pm.toUpperCase().equals("PM"))
            if (!s.substring(0,2).equals("12"))
                inth = (inth + 12);


        String newhour = String.format ("%d", inth);
        if (pm.toUpperCase().equals("AM") && s.substring(0,2).equals("12"))
            return "00" + s.substring(2,s.length()-2);
        else
            if (inth < 10)
                return "0" + newhour + s.substring(2,s.length()-2);
            else
                return newhour + s.substring(2,s.length()-2);



    }
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /*
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();*/
        System.out.println(timeConversion("04:59:59AM"));
    }
}