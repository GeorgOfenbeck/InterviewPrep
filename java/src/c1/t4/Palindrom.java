package c1.t4;


import java.util.HashMap;

public class Palindrom {
    public static void main(String[] args) {

        String str = "Tact Coa";
        Palindrom pal = new Palindrom();
        if (pal.checkpal(str) == true)
            System.out.println("works");
        else
            System.out.println("fail");
    }

    boolean checkpal(String in) {
        if (in.length() == 0)
            return false;



        Integer length = in.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Character x : in.toCharArray()) {
            Character c = Character.toLowerCase(x);
            if (c == ' ')
                length--;
            else
                if (map.containsKey(c))
                    map.put(c, map.get(c) + 1);
                else
                    map.put(c, 1);
        }

        boolean hasmiddle = false;
        if (length % 2 == 0) {
            //even case
            hasmiddle = false;
        } else hasmiddle = true;


        for (Integer i : map.values()) {
            if (i % 2 != 0) {
                if (hasmiddle == true) {
                    hasmiddle = false;
                } else return false;
            }


        }
        return true;

    }
}
