/*
package algorithms;

import java.util.*;
import sun.awt.image.ImageWatched.Link;

public class BinSearch {


  public static void main(String[] args){

    LinkedList<Integer> ll = new LinkedList();
    TreeSet<Integer> ts = new TreeSet<>();
    HashSet<Integer> hs = new HashSet<>();
    ArrayList<Integer> al = new ArrayList<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    Random rand = new Random();

    for (int i = 0; i < 10000; i++){
      Integer value = rand.nextInt();

      ll.add(value);
      ts.add(value);
      hs.add(value);
      al.add(value);
      pq.add(value);
    }



    Long start, stop;

    Integer[] arr = {99,1,2,3,4,5,6};
    //Integer[] arr = al.toArray(new Integer[(al.size())]);

    Arrays.sort(arr);
    Integer search = arr[arr.length/2];
    System.out.println(search);
    Integer xx = Arrays.binarySearch(arr,search);

    Integer yy = Arrays.binarySearch(arr, search, (Integer p1, Integer p2) -> p1.compareTo(p2));
    Integer zz = Arrays.binarySearch(arr, search, Comparator.naturalOrder());
    Integer bg = Arrays.binarySearch(arr,8,Comparator.naturalOrder());

    System.out.println(xx);
    System.out.println(yy);
    System.out.println(zz);
    System.out.println(bg);
    System.out.println(arr.length);
    System.out.println(bg * -1 -1);

    BinSearch sol = new BinSearch();
    Integer aa = sol.myBinSearch(arr,0,arr.length-1,3, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
      }
    });
    System.out.println(aa);

  }


  int myBinSearch(Integer [] arr, int left, int right,int target, Comparator<Integer> c){

    if (left > right)
      return left;
    int middle = left + (right - left) /2;
    int cur = arr[middle];
    int cmp = c.compare(cur, target);
    if (cmp == 0 && middle +1 < arr.length && c.compare(arr[middle + 1],target) != 0) {
      return middle;
    } else {
      if (cmp > 0 )
        return myBinSearch(arr, left, middle-1, target,c);
        else
          return myBinSearch(arr,middle+1,right,target,c);
    }
  }



}
*/