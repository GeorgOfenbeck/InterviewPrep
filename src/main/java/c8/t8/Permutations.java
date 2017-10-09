package c8.t8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;



public class Permutations {

    //= new ArrayList<String>();



        static ArrayList<String> permute(ArrayList<Character> in, ArrayList<String> ret, Stack<Character> cur) {


        if (in.size() == 0){
            StringBuffer buffer = new StringBuffer();
            while (!cur.isEmpty())
                buffer.append(cur.pop());
            ret.add(buffer.toString());
        }

        else {
            for (int i = 0; i < in.size(); i++) {
                Character t = in.get(i);
                cur.push(t);
                ArrayList<Character> cpy = new ArrayList<>();
                cpy.addAll(in);


            }
        }
        return ret;
    }
}
