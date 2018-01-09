package elemintprep.belt2425;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class Help implements Comparable<Help>{
  int val;
  int idx;
  Help(int pval, int pindex){
    val = pval;
    idx = pindex;
  }

  @Override
  public int compareTo(Help other){
    return Integer.compare(val,other.val);
  }


}
public class CountInversions {
  // @include


  public static int countInversions(List<Integer> A) {
    ArrayList<Help> orig = new ArrayList<>(A.size());
    ArrayList<Help> sorted = new ArrayList<>(A.size());

    for (int i = 0; i < A.size(); i++){
      orig.set(i,new Help(A.get(i),i));
      sorted.set(i,new Help(A.get(i),i));
    }

    sorted.sort(new Comparator<Help>() {
      @Override
      public int compare(Help o1, Help o2) {
        return o1.compareTo(o2);
      }
    });

    return 1;


  }

  // @exclude

  // O(n^2) check of inversions.
  private static int n2Check(List<Integer> a) {
    int count = 0;
    for (int i = 0; i < a.size(); ++i) {
      for (int j = i + 1; j < a.size(); ++j) {
        if (Integer.compare(a.get(i), a.get(j)) > 0) {
          ++count;
        }
      }
    }
    System.out.println(count);
    return count;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(2000001) - 1000000);
      }
      assert(n2Check(A) == countInversions(A));
    }
  }
}
