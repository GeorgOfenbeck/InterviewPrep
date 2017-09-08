package c1.t1;

import java.util.*;

class Unique {
    boolean detectunique(String in) {
        Set<Character> set = new HashSet<Character>();
        for (Character i : in.toCharArray())
            set.add(i);
        if (set.size() == in.length())
            return true;
        else
            return false;
    }

    boolean detectuniquegen(Iterable<Object> in) {
        Set<Object> set = new HashSet<Object>();
        for (Object o : in){
            if (set.contains(o))
                return false;
            else
                set.add(o);
        }
        return true;
    }

    boolean detectunique2(String in) {
        return false;

    }
}