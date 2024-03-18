package c5.t2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Flip {



    public static Integer getBit(Integer in, Integer pos){
        return ((1 << pos) & in);
    }

    public static Integer flipwin(Integer in){

        Integer longest = 0;
        Integer fliplength = 0;
        Integer seqlength = 0;
        Integer lastflip = 0;
        Integer longestflip = 0;
        for(int i = 0; i < 32; i++){
            Integer focus = getBit(in,i);
            if (focus == 0){
                if (fliplength > longest)
                    longestflip = lastflip;
                fliplength = seqlength;
                lastflip = i;
            } else{
                seqlength++;
                fliplength++;
            }
        }
        if (fliplength > longest)
            longestflip = lastflip;

        return longestflip;
    }
}
