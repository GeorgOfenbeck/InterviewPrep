package c1.t2;

import java.util.Arrays;

public class Permute {
    boolean checkpermute(String in, String in2){
        if (in.length() != in2.length())
            return false;
        char[] arr = in.toCharArray();
        Arrays.sort(arr);
        char[] arr2 = in2.toCharArray();
        Arrays.sort(arr2);

        for (int i = 0; i < arr.length; i++){
            if (arr[i] != arr2[i])
                return false;
        }
        return true;
    }
}
