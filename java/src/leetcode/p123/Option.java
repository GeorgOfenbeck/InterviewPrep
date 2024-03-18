package leetcode.p123;

/*
import java.util.PriorityQueue;

abstract class Option {
    int profit;

    abstract void add();

    abstract int getProfit();
}


class AddPx extends Option {
    RefinedResult sofar;
    Profit px;

    AddPx(RefinedResult sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        profit = getProfit();
    }

    int getProfit() {
            if(sofar.heap.size() == sofar.k)
                return sofar.profit - sofar.peekSmallest().profit + px.profit;
            else
                return sofar.profit  + px.profit;
    }

    void add() {
        sofar.add(px);
    }
}

class AddNewHeapPx extends Option {
    RefinedResult prev;
    RefinedResult sofar;
    Profit px;

    AddNewHeapPx(RefinedResult prev, RefinedResult sofar, Profit px) {
        this.prev = prev;
        this.px = px;
        this.sofar = sofar;
        profit = getProfit();
    }

    int getProfit() {
        return prev.profit + px.profit;
    }

    void add() {
        sofar.newHeap(px, prev);
    }
}


class MergeLast extends Option {
    RefinedResult sofar;
    Profit px;
    Profit merge;

    MergeLast(RefinedResult sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        merge = new Profit(sofar.lastSelectedProfit.min, px.max);
        profit = getProfit();
    }

    int getProfit() {
        return sofar.profit - sofar.lastSelectedProfit.profit + merge.profit;
    }

    void add() {
        boolean worked = sofar.heap.remove(sofar.lastSelectedProfit);
        sofar.profit = sofar.profit - sofar.lastSelectedProfit.profit;
        sofar.add(merge);
    }
}

class MergeLastNonSelected extends Option {
    RefinedResult sofar;
    Profit px;
    Profit merge;

    MergeLastNonSelected(RefinedResult sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        merge = new Profit(sofar.lowestNonSelectMin.min, px.max);
        profit = getProfit();
    }

    int getProfit() {
        int sum = 0;
        if (sofar.discardheap != null) {
            for (Profit p : sofar.discardheap)
                sum = sum + p.profit;
        }
        return sum + merge.profit;
        //return sofar.profit - sofar.peekSmallest().profit + merge.profit;
    }

    void add() {
        if (sofar.discardheap != null) {
            sofar.heap.clear();
            sofar.heap.addAll(sofar.discardheap);
            sofar.profit = profit - merge.profit;
        }
        sofar.add(merge);
    }
}

class Discard extends Option {
    RefinedResult sofar;
    RefinedResult prev;
    Profit px;

    Discard(RefinedResult sofar, Profit px, RefinedResult prev) {
        this.sofar = sofar;
        this.px = px;
        this.prev = prev;
        profit = getProfit();
    }

    int getProfit() {
        return sofar.profit;
    }

    void add() {
        if (sofar.lowestNonSelectMin == null || sofar.lowestNonSelectMin.min.x >= px.min.x) {
            sofar.lowestNonSelectMin = px;
            if (prev != null) {
                sofar.discardheap = new PriorityQueue<>();
                sofar.discardheap.addAll(prev.heap);
            }
        }
    }
}

*/