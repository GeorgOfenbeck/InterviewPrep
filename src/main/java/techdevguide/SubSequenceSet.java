package techdevguide;

import java.rmi.UnexpectedException;
import java.util.*;

public class SubSequenceSet {


  public static void main(String[] args) {
    Set<String> set = new HashSet<>();
    set.add("able");
    set.add("ale");
    set.add("apple");
    set.add("bale");
    set.add("kangaroo");
    String d = "abppplee";

    SubSequenceSet ss = new SubSequenceSet();
    String res = ss.findLongestSubsequence(d, set);

    System.out.println(res);
  }

  private class Progress {

    String original;
    Boolean done = false;
    Integer pos;

    Progress(String o) {
      original = o;
      pos = 0;
    }

    boolean update() {
      pos = pos + 1;
      if (pos >= original.length()) {
        done = true;
      }
      return done;
    }

    Character getChar() {
      if (pos >= original.length()) {
        throw new OutOfMemoryError();
      }
      return original.charAt(pos);

    }
  }

  void updateworkingSet(HashMap<Character, HashSet<Progress>> workingset, Character cur,
      Progress p) {
    if (workingset.containsKey(cur)) {
      HashSet<Progress> tmphs = workingset.get(cur);
      tmphs.add(p);
    } else {
      HashSet<Progress> tmphs = new HashSet<>();
      tmphs.add(p);
      workingset.put(cur, tmphs);
    }
  }

  String findLongestSubsequence(String s, Set<String> d) {

    TreeMap<Integer, String> results = new TreeMap<>(Collections.reverseOrder());
    HashMap<Character, HashSet<Progress>> workingset = new HashMap<>();

    //initalize working set
    d.stream().forEach(cur -> {
      if (cur.length() > 0) {
        updateworkingSet(workingset, cur.charAt(0), new Progress(cur));
      }
    });

    s.chars().forEach(ci -> {
      char c = (char) ci;
      if (workingset.containsKey(c)) { //we have a match
        HashSet<Progress> hs = workingset.get(c);
        HashSet<Progress> np = new HashSet<>();
        hs.forEach(p -> {
          p.update();
        });
        hs.stream().forEach(p -> {
          if (p.done) {
            results.put(p.original.length(), p.original); //if we dont care which
          } else {
            np.add(p);
          }
        });
        workingset.remove(c);
        np.stream().forEach(p -> {
              Character nc = p.getChar();
              updateworkingSet(workingset, nc, p);
            }
        );
      }
    });
  StringBuilder sb = new StringBuilder();
    TreeSet<Integer> ts = new TreeSet<Integer>();
    int[] a = new int[ts.size()];
    Arrays.stream(a).forEach(p -> ts.add(p));
    Integer[] b = new Integer[ts.size()];
    ts.toArray(b);

    String entry = results.firstEntry().getValue();
    LinkedList<Integer> ll = new LinkedList<>();
    ll.stream().reduce( 0, (x,y) ->  {return x + y;});
    return entry;
  }

}
