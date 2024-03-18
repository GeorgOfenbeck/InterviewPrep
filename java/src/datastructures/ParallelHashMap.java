package datastructures;

import java.util.*;


class Stuff{

}

public class ParallelHashMap {

  HashMap<Integer,Integer> hmap;
  class Entry{
    Stuff key;
    Integer value;
  }

  ArrayList<Entry> map;


  public void put(Stuff val){
    int hash = val.hashCode();

    int pos = hash % map.size();

  }



}
