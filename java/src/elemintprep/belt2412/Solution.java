package elemintprep.belt2412;

import java.util.*;

class Blub{
  int val;

  Blub(int pval){
    val = pval;
  }
}

class Solution{
  public static class TrafficElement implements Comparable<TrafficElement> {
    public int time;
    public double volume;

    public TrafficElement(int time, double volume) {
      this.time = time;
      this.volume = volume;
    }

    @Override
    public int compareTo(TrafficElement o) {
      int volumeCmp = Double.compare(volume, o.volume);
      return volumeCmp != 0 ? volumeCmp : time - o.time;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      return compareTo((TrafficElement)o) == 0;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(volume, time); }
    // clang-format on
  }


  public static List<TrafficElement> computeTrafficVolumes(
      List<TrafficElement> A, int w) {

    LinkedList<TrafficElement> res = new LinkedList<TrafficElement>();
    TreeSet<TrafficElement> ts = new TreeSet<>();
    for (int i = 0; i < A.size(); i++){

      TrafficElement cur = A.get(i);

      Iterator<TrafficElement> it = ts.iterator();
      Boolean done = false;
      LinkedList<TrafficElement> toremove = new LinkedList<>();
      while (it.hasNext() && !done){
        TrafficElement p = it.next();
        if (p.time < cur.time - w){
          toremove.add(p);
        } else
          done = true;
      }
      for (TrafficElement x : toremove){
        ts.remove(x);
      }
      ts.add(cur);

      Double max = ts.last().volume;

      res.addLast(new TrafficElement(cur.time,max));






    }

    return res;
  }


  public static void main(String[] args) {
    int w = 3;

    TreeSet<Blub> ts = new TreeSet<>();
    ts.add(new Blub(2));
    ts.add(new Blub(2));


    PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

    List<TrafficElement> A = Arrays.asList(
        new TrafficElement(0, 1.3), new TrafficElement(2, 2.5),
        new TrafficElement(3, 3.7), new TrafficElement(5, 1.4),
        new TrafficElement(6, 2.6), new TrafficElement(8, 2.2),
        new TrafficElement(9, 1.7), new TrafficElement(14, 1.1));
    List<TrafficElement> result = computeTrafficVolumes(A, w);
    List<TrafficElement> golden = Arrays.asList(
        new TrafficElement(0, 1.3), new TrafficElement(2, 2.5),
        new TrafficElement(3, 3.7), new TrafficElement(5, 3.7),
        new TrafficElement(6, 3.7), new TrafficElement(8, 2.6),
        new TrafficElement(9, 2.6), new TrafficElement(14, 1.1));
    assert result.equals(golden);
  }
}