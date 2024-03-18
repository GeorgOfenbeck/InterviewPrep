package datastructures;

import java.util.Iterator;

public class List {
  int value;
  List next;

  public Iterator<List> getIt(){
    return new Iterator<List>() {
      List cur = next;
      @Override
      public boolean hasNext() {
        return next != null;
      }

      @Override
      public List next() {
        cur = cur.next;
        return cur;
      }
    };
  }
}


