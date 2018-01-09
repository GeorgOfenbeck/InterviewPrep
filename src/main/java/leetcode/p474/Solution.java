package leetcode.p474;

import java.util.*;

class Solution {

  HashMap<Key, Integer> hmap = new HashMap<>();

  public static void main(String[] args) {
    String[] arr = {"111","1000","1000","1000"}; //{"10","0","1"};//{"10", "0001", "111001", "1", "0"};
    Solution sol = new Solution();
    System.out.println(sol.findMaxForm(arr, 9, 3));

  }

  class Key {

    BitSet bs;
    Pair pair;

    Key(BitSet pbs, Pair ppair) {
      bs = pbs;
      pair = ppair;
    }

    @Override
    public boolean equals(Object pother) {
      if (pother == this) {
        return true;
      }

      if (!(pother instanceof Pair)) {
        return false;
      }

      Key other = (Key) pother;

      return (bs.equals(other.bs) && pair.equals(other.pair));
    }
  }

  class Pair implements Comparable<Pair> {

    Integer zeros = 0;
    Integer ones = 0;

    Pair(Integer z, Integer o) {
      zeros = z;
      ones = o;
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) {
        return true;
      }

      if (!(other instanceof Pair)) {
        return false;
      }

      Pair pother = (Pair) other;

      return pother.zeros.equals(this.zeros) && ((Pair) other).ones.equals(this.ones);
    }

    @Override
    public int compareTo(Pair other) {
      if (other.zeros.equals(zeros) && other.ones.equals(ones)) {
        return 0;
      } else if (other.zeros > zeros) {
        return -1;
      } else if (other.zeros > zeros) {
        return 1;
      } else if (other.ones > ones) {
        return -1;
      } else {
        return 1;
      }
    }
  }

  Pair string2Pair(String s) {
    Pair res = s.chars().mapToObj(ic -> {
      char c = (char) ic;
      if (c == '0') {
        return new Pair(1, 0);
      }
      if (c == '1') {
        return new Pair(0, 1);
      }
      return new Pair(0, 0);
    }).reduce(new Pair(0, 0), (a, b) -> {
      return new Pair(a.zeros + b.zeros, a.ones + b.ones);
    });
    return res;
  }

  public int findMaxForm(String[] strs, int m, int n) {
    LinkedList<Pair> al = new LinkedList<>();
    Arrays.stream(strs).forEach(s -> {
      al.add(string2Pair(s));
    });
    BitSet bs = new BitSet(al.size());

    for (int i = 0; i < al.size(); i++) {
      bs.set(i);
    }
    return findMax(al, m, n, 0, bs);
  }


  int findMax(LinkedList<Pair> al, int m, int n, int sofar, BitSet bs) {
    if (al.isEmpty()) {
      return sofar;
    }
    Integer max;
    BitSet nbs = new BitSet();
    nbs.xor(nbs);
    Key key = new Key(nbs, new Pair(m, n));
    if (hmap.containsKey(key)) {
      System.out.println("cache hit");
      Integer val = hmap.get(key);
      return val;
    } else {
      Pair first = al.removeFirst();
      Integer restm = m - first.zeros;
      Integer restn = n - first.ones;


      Integer with = 0;

      if (restm >= 0 && restn >= 0) {
        if (restn.equals(0) && restm.equals(0)) {
          with = sofar + 1;
        } else {
          bs.set(al.size());
          with = findMax(al, restm, restn, sofar + 1, bs);
        }
      }
      bs.clear(al.size());
      Integer without = findMax(al, m, n, sofar, bs);
      max = Math.max(with, without);

      hmap.put(key, max);
      return max;
    }

  }
}