package algorithms;

public class Heap {

    private int[] Heap;
    private int size;
    private int maxsize;
    private static final int FRONT = 1;


    public Heap(int maxsize){
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }

    private int parent(int pos){
        return pos/2;
    }

    private int leftChild(int pos){
        return (2*pos);
    }

    private int rightChild(int pos){
        return (2 * pos) + 1;
    }

    private void swap(int fpos, int spos){
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private boolean isLeaf(int pos){
        //check why size/2
        if (pos >= (size / 2) && pos < size){
            return true;
        } else
            return false;
    }

    private void minHeapify(int pos){
        if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[leftChild(pos)] || Heap[pos] > Heap [rightChild(pos)]){
                if (Heap[leftChild(pos)] < Heap [rightChild(pos)]){
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                } else {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
            }
        }
    }


    public void insert(int element){
        if (size >= maxsize){
            return;
        }
        Heap[size + 1] = element;
        size ++;
        int current = size;

        while (Heap[current] < Heap[parent(current)]){
                swap(current, parent(current));
                current = parent(current);
        }
    }

    public void print(){
        for (int i = 1; i <= size /2; i ++){
            System.out.print(" PARENT : " + Heap[i]
                    + " LEFT CHILD : " + Heap[2 * i]
                    + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }

    public void minHeap(){
        for (int pos = (size /2 ); pos >= 1; pos --){
            minHeapify(pos);
        }
    }

    public int remove(){
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size - 1];
        size --;
        minHeapify(FRONT);
        return popped;
    }

    // Driver code
    public static void main(String[] arg)
    {
        System.out.println("The Min Heap is ");
        Heap minHeap = new Heap(15);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
        minHeap.minHeap();

        minHeap.print();
        System.out.println("The Min val is " + minHeap.remove());
    }



}
