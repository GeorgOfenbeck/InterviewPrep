package leetcode.p123;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;






class Result {

    Result(int size) {
        k = size;
        sort = new TreeSet<>();
    }

    int k = 0;
    SortedSet<Profit> sort;
    SortedSet<Profit> bsort;
    Profit last;

    Profit lastnonadded = null;

    int profit() {
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        return sum;
    }

    int oldprofit() {
        int sum = 0;
        for (Profit p : bsort)
            sum += p.profit;
        return sum;
    }

    int profitMaxK(Profit px) {
        sort.add(px);
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        sum = sum - sort.last().profit; //remove smallest
        sort.remove(px);
        if (sort.size() > this.k)
            System.out.println("?");
        return sum;
    }

    Profit getLast() {
        return last;
    }

    int profitMaxKwoLast(Profit px) {
        if (sort.size() > this.k)
            System.out.println("?");
        if (last != null) sort.remove(last);
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        if (last != null) sort.add(last);
        if (sort.size() > this.k)
            System.out.println("?");
        return sum + px.profit;
    }

    int profitMaxKwLastNonAddedMin(Profit px){
        if (lastnonadded == null) return Integer.MIN_VALUE;
        Profit merge = new Profit(lastnonadded.min,px.max);
        sort.add(merge);
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        sum = sum - sort.last().profit; //remove smallest
        sort.remove(merge);
        if (sort.size() > this.k)
            System.out.println("?");
        return sum;
    }


    void addSetandResult(Result other, Profit px) {
        sort.clear();
        for (Profit p : other.bsort) {
            sort.add(p);
        }
        boolean x = sort.add(px);
        if (!x)
            System.out.println("?");
        last = px;
        lastnonadded = null;
        if (sort.size() > this.k || (last != null && !sort.contains(last)))
            System.out.println("?");
    }

    void add(Profit px) {
        boolean x = sort.add(px);
        if (!x)
            System.out.println("?");
        //last = px;
        if (sort.size() > this.k) {
            Profit toremove = sort.last();
            if (toremove != px)
                last = px;
            sort.remove(toremove); //remove smallest

            if (toremove == px) {
                if (px.min.x < lastnonadded.min.x)
                    lastnonadded = px;
            } else
                lastnonadded = null;
        }
        if (sort.size() > this.k || (last != null && !sort.contains(last)))
            System.out.println("?");
    }

    void removeLastandAdd(Profit px) {
        if (last != null) sort.remove(last);
        boolean x = sort.add(px);
        if (!x)
            System.out.println("?");
        last = px;
        lastnonadded = null;
        if (sort.size() > this.k || (last != null && !sort.contains(last)))
            System.out.println("?");
    }

    void backup() {
        bsort = new TreeSet<>();
        bsort.addAll(sort);
    }

}