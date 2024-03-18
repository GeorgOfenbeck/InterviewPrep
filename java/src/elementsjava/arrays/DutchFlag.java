package elementsjava.arrays;

import java.util.*;
import java.util.stream.Collectors;

class Descriptor{
    Descriptor(int[] in_arr, int ub, int same_lb, int lb,int us, int in_pivo) {
        smaller_ub = ub; //lower
        same = same_lb; //same
        larger_lb = lb; //higher
        unsorted = us; //unsorted
        arr = in_arr;
        pivot = in_pivo;
    }
    int[] arr;
    int smaller_ub;
    int same;
    int larger_lb;
    int unsorted;
    int pivot;
}

public class DutchFlag {



    public static List<Integer> partition(List<Integer> arr, int pivotidx){

        int pivot = arr.get(pivotidx);

        int unsorted = 0;
        int larger = arr.size()-1;
        int smaller = 0;

        while (unsorted < larger) {
            if (arr.get(unsorted) < pivot) {
                if (smaller == unsorted) {
                    smaller++;
                    unsorted++;
                } else {
                    Collections.swap(arr, unsorted, smaller);
                    smaller++;
                }
            } else {
                if (arr.get(unsorted) > pivot) {
                    Collections.swap(arr, larger, unsorted);
                    larger --;
                }
                else
                    unsorted++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {4,9,8,7,6,4,5,4,3,2,1,4,4};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        list = partition(list,0);

        System.out.println(list);

    }





}
