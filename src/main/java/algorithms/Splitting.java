package algorithms;

public class Splitting {

    public static void split(int [] arr, Integer from, Integer to){
        Integer size = to - from;
        if (size == 0){
            return;
        }

        System.out.print("From " + from + " to: " + to + " Length " + size + " ");
        for (int i = from; i < to; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
        if (size > 1) {
            Integer half = (to - from) / 2;

            split(arr, from, from + half);
            split(arr, from + half, to);
        }
    }

    public static void main(String [] args){

        Splitting splitter = new Splitting();
        int[] arr = new int[]{1,2,3,4,5,6};
        Splitting.split(arr,0,arr.length);
    }




    public static void mergesort(int [] arr) {
        int size = arr.length;


    }

}
