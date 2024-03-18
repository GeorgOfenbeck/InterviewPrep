package generics;

import java.util.HashSet;
import java.util.LinkedList;

public class Generic<T extends Number> {

  public Double plus(T a, T b) {
    return a.doubleValue();
  }

  LinkedList<Integer> ll;

  public static  void main(String[] args) {
    String as = "testaes";
    String bs = "testb";

    StringBuilder sb = new StringBuilder();

    sb.append("blub");


    HashSet<Integer> hs = new HashSet<>();


    System.out.println(as.indexOf("es"));

  }


}
