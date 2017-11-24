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

    System.out.println(xx);


  }






}
