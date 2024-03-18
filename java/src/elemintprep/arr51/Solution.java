package elemintprep.arr51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Solution {

  Random rand = new Random();

  public static void main(String[] args){

    for (int i = 0; i < 100; i++) {
      int[] arr = {3, 1, 3, 8, 1, 1, 2};

      Solution sol = new Solution();
      //sol.dutchFlagPartition(0,arr,0,arr.length);
      sol.qs(arr, 0, arr.length);

      Arrays.stream(arr).forEach(p -> System.out.print(p + " "));
      System.out.println();

    }
  }


  public void swap(int []arr, int a, int b){
    int t = arr[a];
    arr[a] = arr[b];
    arr[b] = t;
  }

  public void dutchFlagPartition(int pivotindex, int[] arr, int start, int stop){

    int pivot = arr[pivotindex];
    //buttom = A[start, smaller]
    //middle = A[smaller,equal]
    //unclassified = A[equal, larger]
    //larger = A[larger, stop]

    int esmaller = start;
    int eequal = start;
    int slarger = stop;

    while (eequal < slarger){
      if (arr[eequal] < pivot){
        swap(arr,esmaller,eequal);
        esmaller++;
        eequal++;
      } else {
        if (arr[eequal] > pivot){
          slarger--;
          swap(arr,eequal,slarger);
        } else { //==
          eequal++;
        }
      }
    }
  }


  public void qs(int[] arr, int start, int stop){

    if (start == stop)
      return;
    if (stop - start == 1)
      return;

    if (stop < start )
      assert(false);



    //5,2
    int length = stop - start;
    int pivindex = rand.nextInt(length);

    if (pivindex+start >= arr.length)
      System.out.print("?");
    int pivot = arr[pivindex+start];
    //buttom = A[start, smaller]
    //middle = A[smaller,equal]
    //unclassified = A[equal, larger]
    //larger = A[larger, stop]

    int esmaller = start;
    int eequal = start;
    int slarger = stop;

    while (eequal < slarger){
      if (arr[eequal] < pivot){
        swap(arr,esmaller,eequal);
        esmaller++;
        eequal++;
      } else {
        if (arr[eequal] > pivot){
          slarger--;
          swap(arr,eequal,slarger);
        } else { //==
          eequal++;
        }
      }
    }

    qs(arr,start,esmaller);
    qs(arr,eequal,stop);

  }


}
