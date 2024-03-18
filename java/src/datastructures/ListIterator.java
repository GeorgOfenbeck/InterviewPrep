package datastructures;

import java.util.Iterator;

public class ListIterator implements Iterator<List> {

  List cur = null;

  ListIterator(List start) {
    cur = start;
  }

  @Override
  public boolean hasNext() {
    return cur != null;
  }

  @Override
  public List next() {
    cur = cur.next;
    return cur;
  }

}
