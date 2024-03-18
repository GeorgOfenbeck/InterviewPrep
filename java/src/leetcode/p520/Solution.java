package leetcode.p520;


        import java.util.Iterator;
        import java.util.*;


class Solution {
    public static void main(String[] args) {

    }
    public boolean detectCapitalUse(String word) {
        char start = word.charAt(0);
        if (Character.isUpperCase(start)){
            if (word.length() == 1)
                return true;
            else{
                if (Character.isUpperCase(word.charAt(1))){
                    int it = 2;
                    boolean upper = true;
                    while (it < word.length() && upper == true)
                        if(Character.isUpperCase(word.charAt(it)))
                            it ++;
                        else
                            upper = false;

                    return upper;
                } else {
                    int it = 2;
                    boolean lower = true;
                    while (it < word.length() && lower == true)
                        if (Character.isLowerCase(word.charAt(it)))
                            it++;
                        else
                            lower = false;

                    return lower;
                }
            }

            }
            else
        {
            int it = 1;
            boolean lower = true;
            while (it < word.length() && lower == true)
                if (Character.isLowerCase(word.charAt(it)))
                    it++;
                else
                    lower = false;

            return lower;
        }
    }

}
