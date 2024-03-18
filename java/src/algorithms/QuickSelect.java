package algorithms;

import java.util.*;

public class QuickSelect {

  public static void main(String[] args){
    QuickSelect qs = new QuickSelect();
    //Integer[] arr = {0,1,2,3,4,5,6};
    Integer[] arr = {6,5,4,3,2,1,0};
    List<Integer> ll = Arrays.asList(arr);


    for (int i = 0; i < arr.length; i++){
      Integer res = qs.selectkth(ll,i);
      System.out.println(res);

    }
  }

  private class Ret{
    LinkedList<Integer> left = new LinkedList<>();
    LinkedList<Integer> right = new LinkedList<>();

  }

  private Random rand;
  QuickSelect(){
    rand = new Random();
  }



  Ret swap(int pivot, List<Integer> arr){
    Ret ret = new Ret();
    arr.stream().forEach((Integer p) -> {
      if (p < pivot){
        ret.left.add(p);
      } else {
        if (p == pivot) {
          //we omit this on purpose
        }
        else
          ret.right.add(p);
      }
    });
    return ret;
  }
  /*
      0 1 2 3 4 5 6
   */

  int selectkth(List<Integer> arr, Integer k){
    Integer pivot = arr.get(rand.nextInt(arr.size()));
    Ret ret = swap(pivot,arr);

    Integer ls = ret.left.size();
    Integer rs = ret.right.size();
    Integer ms = arr.size() - ls - rs;

    if (ret.left.size()-1 >= k){
      return selectkth(ret.left,k);
    } else {
      if ((ls + ms - 1) < k){
        return selectkth(ret.right,k-ls - ms);
      } else
        return pivot;
    }
  }
}
