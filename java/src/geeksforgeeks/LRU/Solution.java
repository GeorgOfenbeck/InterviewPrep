package geeksforgeeks.LRU;


import java.util.*;

public class Solution {


  public static void main(String[] args){
    LinkedHashMap<Integer,Integer> lm = new LinkedHashMap<>();
    lm.put(1,1);
    lm.put(2,2);
    lm.put(3,3);

    lm.keySet().stream().forEach(k -> System.out.println(k));
    System.out.println("-----------");
    Iterator<Integer> it = lm.keySet().iterator();
    if (it.hasNext()) System.out.println(it.next());
    System.out.println(" - - - - - - - -");
    lm.remove(2);
    lm.keySet().stream().forEach(k -> System.out.println(k));
    System.out.println("-----------");
    it = lm.keySet().iterator();
    if (it.hasNext()) System.out.println(it.next());
    System.out.println(" - - - - - - - -");
    lm.put(2,2);
    lm.keySet().stream().forEach(k -> System.out.println(k));
    System.out.println("-----------");
    it = lm.keySet().iterator();
    if (it.hasNext()) System.out.println(it.next());
    System.out.println(" - - - - - - - -");

    lm.put(1,1);
    lm.keySet().stream().forEach(k -> System.out.println(k));
    System.out.println("-----------");
    it = lm.keySet().iterator();
    if (it.hasNext()) System.out.println(it.next());
    System.out.println(" - - - - - - - -");

    String s = "a";
    String b = s.substring(1);

    System.out.println(b.length());
    String c = "";

  }

}
