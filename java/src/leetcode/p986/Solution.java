package leetcode.p986;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

 class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
}

class Solution {
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        ArrayList<Interval> res = new ArrayList<>();
        Iterator<Interval> ait = Arrays.stream(A).iterator();
        Iterator<Interval> bit = Arrays.stream(B).iterator();


        Interval a = null;
        Interval b = null;
        if (ait.hasNext())
            a = ait.next();
        if (bit.hasNext())
            b = bit.next();

        while( a != null && b != null) {
            if (a.end < b.start){
                if (ait.hasNext())
                    a = ait.next();
                else
                    a = null;
            } else {
                if (b.end < a.start){
                    if (bit.hasNext())
                        b = bit.next();
                    else
                        b = null;
                } else {
                    //they intersect
                    if (a.start < b.start) { //a starts first
                        if (b.end <= a.end) { //b is fully in a
                            res.add(new Interval(b.start,b.end));
                            if (bit.hasNext())
                                b = bit.next();
                            else
                                b = null;
                        } else { //b starts after a but a ends first
                            res.add(new Interval(b.start,a.end));
                            if (ait.hasNext())
                                a = ait.next();
                            else
                                a = null;
                        }
                    } else { //b starts first
                        if (a.end <= b.end) { //a is fully in a
                            res.add(new Interval(a.start,a.end));
                            if (ait.hasNext())
                                a = ait.next();
                            else
                                a = null;
                        } else { //a starts after b put b ends first
                            res.add(new Interval(a.start,b.end));
                            if (bit.hasNext())
                                b = bit.next();
                            else
                                b = null;
                        }
                    }
                }
            }
        }
        return res.toArray(new Interval[res.size()]);

    }

}
