package generics;

import java.util.LinkedList;

public class Generic<T extends Number>{

  public Double plus(T a, T b){
    return a.doubleValue();
  }

  LinkedList<Integer> ll;
}
