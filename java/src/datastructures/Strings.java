package datastructures;

public class Strings {


    public static void main(String[] args){
        String s = "testing";

        String intval = "汉";

        Character a = intval.charAt(0);
        System.out.println();

        String t = "testing";

        System.out.println(s == t);
        System.out.println(s.compareTo(t));

        System.out.println(s.contains("esting"));
        System.out.println(s.contains("xxx"));

        System.out.println(s.indexOf("ing",1));

        String[] splits = s.split("s");
        System.out.println(splits[0]);
        System.out.println(splits[1]);

        System.out.println(s.startsWith("esting",1));

        System.out.println(s.substring(1,3));

        System.out.println(s.substring(1));

        System.out.println(s.trim());

        StringBuilder sb = new StringBuilder();





    }




}
