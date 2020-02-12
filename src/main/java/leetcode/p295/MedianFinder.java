package leetcode.p295;


import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {


    public static void main(String [] args){
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
    }

    PriorityQueue<Integer> minheap;
    PriorityQueue<Integer> maxheap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minheap = new PriorityQueue<>();
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (maxheap.size() == 0 || num <= maxheap.peek()){
            maxheap.add(num);
        } else {
            minheap.add(num);
        }

        int minsize = minheap.size();
        int maxsize = maxheap.size();

        if (minsize > maxsize ){
            int ele = minheap.poll();
            maxheap.add(ele);
        }
        if (maxsize > minsize + 1){
            int ele = maxheap.poll();
            minheap.add(ele);
        }
    }

    public double findMedian() {
        int size = minheap.size() + maxheap.size();
        if (size % 2 == 0){
            return (minheap.peek() + maxheap.peek()) / 2.0;
        } else {
            return maxheap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
