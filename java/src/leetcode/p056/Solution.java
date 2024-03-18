package leetcode.p056;

import java.util.ArrayList;
import java.util.List;

class Interval implements Comparable<Interval> {
    int start;
    int end;

    Interval(int start, int end) {
        this.end = end;
        this.start = start;
    }

    Interval(int[] arr) {
        this.start = arr[0];
        this.end = arr[1];
    }


    @Override
    public int compareTo(Interval o) {
        if (this.start < o.start)
            return -1;
        else if (this.start == o.start)
            return 0;
        else
            return 1;
    }
}

class Solution {
    public static void main(String [] args){
        int[][] test = {{1,4},{0,1}};
        Solution sol = new Solution();
        sol.merge(test);
    }

    public int[][] merge(int[][] intervals) {

        if (intervals == null) return null;
        if (intervals.length == 0) return intervals;




        List<Interval> convert = new ArrayList<>();
        for (int i = 0; i < intervals.length ; i++) {
            convert.add(new Interval(intervals[i]));
        }
        convert.sort( (a,b) -> {
            if (a.start < b.start)
                return -1;
            else if (a.start == b.start)
                return 0;
            else
                return 1;
        });

        Interval last = convert.get(0);

        List<Interval> result = new ArrayList<>();

        for (int i = 1; i < convert.size() - 1; i++) {
            Interval next = convert.get(i);
            if (last.end < next.start) {
                result.add(last);
                last = next;
            }
            if (last.end >= next.start) {
                if (last.end < next.end)
                    last = new Interval(last.start, next.end);
                else
                    last = new Interval(last.start, last.end);
            }
        }
        Interval next = convert.get(convert.size()-1);
        if (last.end < next.start) {
            result.add(last);
            last = next;
        }
        if (last.end >= next.start) {
            if (last.end < next.end)
                last = new Interval(last.start, next.end);
            else
                last = new Interval(last.start, last.end);
            result.add(last);
        }

        int[][] resarr = new int[result.size()][];

        for (int i = 0; i < result.size(); i++) {
            resarr[i] = new int[2];
            resarr[i][0] = result.get(i).start;
            resarr[i][1] = result.get(i).end;
        }
        return resarr;
    }
}