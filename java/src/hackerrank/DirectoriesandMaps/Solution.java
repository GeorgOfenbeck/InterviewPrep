package hackerrank.DirectoriesandMaps;


//Complete this code or write your own from scratch
import java.util.*;
import java.io.*;

class Solution{
    public static void main(String []argh){
        HashMap<String, Integer> hmap = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++){
            String name = in.next();
            int phone = in.nextInt();
            hmap.put(name,phone);
            // Write code here


        }
        while(in.hasNext()){
            String s = in.next();
            String ret;
            if (hmap.containsKey(s))
                ret = s + "=" + hmap.get(s).toString();
            else
                ret = "Not found";
            System.out.println(ret);
            // Write code here
        }
        in.close();
    }
}

