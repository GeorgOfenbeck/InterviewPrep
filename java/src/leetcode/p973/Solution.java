package leetcode.p973;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    class Entry{
        double distance;
        int [] point;
        Entry(double distance, int[] point) {
            this.distance = distance;
            this.point = point;
        }

    }

    public static void main(String[] varg){
        int[][] points =  {{1,3},{-2,2}};
        int K = 1;
        Solution sol = new Solution();
        sol.kClosest(points,K);
    }

    public Entry createEntry(int [] point){
        int x= point[0];
        int y = point[1];
        double distance = Math.sqrt(x*x + y*y);
        return new Entry(distance,point);
    }

    public int[][] kClosest(int[][] points, int K) {

        PriorityQueue<Entry> pq = new PriorityQueue<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.distance < o2.distance)
                    return -1;
                else if (o1.distance > o2.distance)
                    return 1;
                else
                return 0;
            }
        });
        for (int i = 0; i< points.length; i++){
            pq.add(createEntry(points[i]));
        }


        int[][] result = new int[K][];

        for (int i = 0; i < K; i++){
            result[i] = pq.remove().point;
        }


        return result;
    }

}
