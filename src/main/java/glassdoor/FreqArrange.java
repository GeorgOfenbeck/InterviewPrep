package glassdoor;

import java.util.*;
import sun.awt.image.ImageWatched.Link;

public class FreqArrange {

  class KeyPair implements Comparable<KeyPair> {

    int value;
    int freq;

    KeyPair(int pfreq, int pvalue){
      value = pvalue;
      freq = pfreq;
    }

    @Override
    public int compareTo(KeyPair other){
      if (freq < other.freq){
        return -1;
      } else{
        if(freq > other.freq){
          return 1;
        } else{
          if (value < other.value){
            return -1;
          } else {
            if (value > other.value)
              return 1;
            else
              return 0;
          }
        }
      }
    }

  }


  public static void main(String[] args){
    FreqArrange fa = new FreqArrange();
    LinkedList<Integer> ll = new LinkedList<>();
    ll.add(1);
    ll.add(2);
    ll.add(1);
    ll.add(3);
    LinkedList<Integer> lf = fa.upfre(ll);
    lf.stream().forEach(p -> {
      System.out.println(p);
    });
  }


  LinkedList<Integer> upfre(LinkedList<Integer> in){

    HashMap<Integer,Integer> freq = new HashMap<>();
    LinkedList<Integer> ret = new LinkedList<>();

    in.stream().forEach(p -> {
      if (freq.containsKey(p)) {
        Integer sofar = freq.get(p);
        freq.put(p, sofar+1);
      }
      else
        freq.put(p,1);
    });

    PriorityQueue<KeyPair> min = new PriorityQueue<>();
    freq.entrySet().stream().forEach( e ->{
      min.add(new KeyPair(e.getValue(),e.getKey()));
    });

    while (!min.isEmpty()){
      KeyPair p = min.poll();
      Integer val = p.value;
      Integer valfreq = p.freq;

      for (int i = 0; i< valfreq; i++){
        ret.add(val);
      }
      min.remove(p);
    }



    return ret;
  }
}
