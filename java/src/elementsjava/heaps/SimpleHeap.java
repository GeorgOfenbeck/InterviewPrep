package elementsjava.heaps;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SimpleHeap {

    class Child {
        Integer idx;
        Integer value;
    }

    ArrayList<Integer> store = new ArrayList<>();

    void add(int value) {
        store.add(value); //at the end
        bubbleUp();
    }

    Integer getMin() {
        if (store.isEmpty()) return null;
        else {
            Integer ret = store.get(0);
            if (store.size() > 1){
                store.set(0, store.get(store.size() - 1));
                store.remove(store.size() - 1);
                bubbleDown();
            } else
                store.remove(store.size() - 1);

            return ret;
        }
    }

    int getParent(int idx) {
        if (idx == 0) return 0;
        else {
            int half = (idx - 1) / 2;
            return half;
        }
    }


    void swap(int parentidx, int childidx) {
        int tmp = store.get(parentidx);
        store.set(parentidx, store.get(childidx));
        store.set(childidx, tmp);
    }


    void bubbleDown() {
        int childidx;
        int parentidx = 0;
        int childele1;
        int childele2;
        int parentele;


        for (boolean cont = true; cont; ) {
            parentele = store.get(parentidx);
            childidx = parentidx * 2;

            if (childidx + 2 < store.size()) {
                childele1 = store.get(childidx + 1);
                childele2 = store.get(childidx + 2);
                if (childele1 < childele2) {
                    childidx = childidx + 1;
                } else {
                    childidx = childidx + 2;
                }

            } else {
                if (childidx + 1 < store.size()) {
                    childidx = childidx + 1;
                }
                else {
                    cont = false;
                }
            }
            if (cont){
                int child = store.get(childidx);
                if (child < parentele) {
                    swap(parentidx, childidx);
                    parentidx = childidx;
                }
                else {
                    cont = false;
                }
            }
        }
    }

    void bubbleUp() {
        int childidx = store.size() - 1;
        int parentidx;
        int childele;
        int parentele;

        do {
            parentidx = getParent(childidx);
            childele = store.get(childidx);
            parentele = store.get(parentidx);


            if (childele < parentele) {
                swap(childidx, parentidx);
                childidx = parentidx;
            }
        } while (childele < parentele);
    }

    public static void main(String[] args) {
        SimpleHeap cc = new SimpleHeap();

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) return -1; else
                    if (o1 == o2) return 0;
                    else return 1;
            }
        });



        for (int i = 0; i < 10; i++){
            cc.add(10 - i);
            queue.add(10-i);
        }


        Integer val;
        do {
            val = cc.getMin();
            System.out.println(val);
        } while (val != null);

        while(!queue.isEmpty()){
            val = queue.poll();
            System.out.println(val);
        }

        System.out.println(cc.store);
    }
}
