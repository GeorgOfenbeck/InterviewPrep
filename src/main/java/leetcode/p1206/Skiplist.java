package leetcode.p1206;

import java.util.*;


public class Skiplist {

    public static void main(String[] args) {
        /*
        ["Skiplist","add","add","add","add","add","erase","erase","add","search","search","add","erase","search","add","add","add","erase","search","erase","search","search","search","erase","erase","search","erase","add","add","erase","add","search","search","search","search","search"]
[[],[9],[4],[5],[6],[9],[2],[1],[2],[7],[4],[5],[6],[5],[6],[7],[4],[3],[6],[3],[4],[3],[8],[7],[6],[7],[4],[1],[6],[3],[4],[7],[6],[1],[0],[3]]

         */
        Skiplist skiplist = new Skiplist();
        skiplist.add(0);
        skiplist.printSkipFullList();
        skiplist.add(0);
        //skiplist.add(1);
        //skiplist.printSkipFullList();
        //skiplist.erase(3);
        skiplist.printSkipFullList();
        skiplist.erase(0);
        skiplist.printSkipFullList();
        skiplist.erase(0);
        skiplist.printSkipFullList();


/*        skiplist.add(2);
        skiplist.printSkipFullList();
        skiplist.erase(1);
        skiplist.printSkipFullList();
        skiplist.erase(1);
        skiplist.add(2);
        skiplist.add(1);
        skiplist.erase(1);
        skiplist.erase(1);
        skiplist.erase(2);
        skiplist.printSkipFullList();
*/

/*
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.printSkipFullList();
        skiplist.add(2);
        skiplist.printSkipFullList();
        skiplist.add(3);
        skiplist.printSkipFullList();
        System.out.println(skiplist.search(0));   // return false.
        skiplist.printSkipFullList();
        skiplist.add(4);
        skiplist.printSkipFullList();
        System.out.println(skiplist.search(1));   // return true.
        skiplist.printSkipFullList();
        System.out.println(skiplist.erase(0));    // return false, 0 is not in skiplist.
        skiplist.printSkipFullList();
        System.out.println(skiplist.erase(1) == true);    // return true.
        skiplist.printSkipFullList();
        System.out.println(skiplist.search(1));   // return false, 1 has already been erased.)
        skiplist.printSkipFullList();

 */
    }

    class NumberNode {
        NumberNode next = null;
        NumberNode prev = null;
        NumberNode down = null;
        int value;

        NumberNode(int value) {
            this.value = value;
        }
    }

    Random gen = new Random();
    //NotSoRandom gen = new NotSoRandom();

    public LinkedList<NumberNode> layers = new LinkedList<>();
    // 0 bottom layer -> n top most layer

    public void printRow(NumberNode p) {
        NumberNode cur = p;

        while (cur.prev != null)
            cur = cur.prev;
        while (cur != null) {
            if (cur == p)
                System.out.print("*" + cur.value + " -> ");
            else
                System.out.print(" " + cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void printSkipFullList() {
        layers.forEach(p -> printRow(p));
        System.out.println("---------------");

    }

    /**
     * We stop at a value that is target or smaller
     *
     * @param layer
     * @param target
     * @return
     */
    private NumberNode searchHorizontal_forward(NumberNode layer, int target) {
        NumberNode cur = layer;
        while (cur.next != null && cur.value != target && cur.next.value <= target)
            cur = cur.next;
        return cur;
    }

    /**
     * We stop at a value that is target or bigger
     *
     * @param layer
     * @param target
     * @return
     */
    private NumberNode searchHorizontal_backward(NumberNode layer, int target) {
        NumberNode cur = layer;
        while (cur.prev != null && cur.value != target && cur.prev.value >= target)
            cur = cur.prev;
        return cur;
    }


    //bottom -> Top

    /**
     * @param target
     * @return
     */
    private LinkedList<NumberNode> searchVerticalList(int target) {
        LinkedList<NumberNode> ret = new LinkedList<>();
        NumberNode top = layers.getLast();
        NumberNode layerresult;
        if (top.value < target)
            layerresult = searchHorizontal_forward(top, target);
        else
            layerresult = searchHorizontal_backward(top, target);

        ret.addFirst(layerresult);
        while (layerresult.down != null) {
            if (layerresult.down.value > target)
                layerresult = searchHorizontal_backward(layerresult.down, target);
            else
                layerresult = searchHorizontal_forward(layerresult.down, target);
            ret.addFirst(layerresult);
        }
        return ret;
    }

    class NotSoRandom {
        int count = 0;
        int height = 1;
        boolean nextBoolean() {
            if (count == height) {
                count = 0;
                height = 3;
                return false;
            } else {
                count++;
                return true;
            }

        }
    }

    public Skiplist() {

    }

    public boolean search(int target) {
        LinkedList<NumberNode> vert = searchVerticalList(target);
        if (vert.getFirst().value == target) return true;
        else return false;
    }

    public void add(int num) {
        if (layers.isEmpty()) {
            NumberNode n = new NumberNode(num);
            layers.add(n);
            NumberNode down = n;
            while (gen.nextBoolean()) {
                NumberNode nu = new NumberNode(num);
                layers.add(nu);
                nu.down = down;
                down = nu;
            }
        } else {
            LinkedList<NumberNode> vertlist = searchVerticalList(num); //bottom to top

            boolean cont = true;
            NumberNode prev = null;

            for (NumberNode neigh : vertlist) {
                if (!cont) break;
                cont = gen.nextBoolean();
                NumberNode nu = new NumberNode(num);
                nu.down = prev;
                if (neigh.value > num) {
                    if (neigh.prev == null) {
                        neigh.prev = nu;
                        nu.next = neigh;
                    } else {
                        nu.prev = neigh.prev;
                        nu.next = neigh;
                        neigh.prev = nu;
                        nu.prev.next = nu;
                    }
                } else {
                    if (neigh.next == null) {
                        neigh.next = nu;
                        nu.prev = neigh;
                    } else {
                        nu.next = neigh.next;
                        nu.prev = neigh;
                        nu.next.prev = nu;
                        neigh.next = nu;
                    }
                }
                prev = nu;
            }
            if (cont)
                while (gen.nextBoolean()) {
                    NumberNode nu = new NumberNode(num);
                    nu.down = prev;
                    layers.add(nu);
                    prev = nu;
                }
        }
    }

    private void cleanLayers(HashSet<NumberNode> vert) {
        ListIterator<NumberNode> li = layers.listIterator();
        while (li.hasNext()) {
            NumberNode n = li.next();
            if (vert.contains(n)) {
                if (n.prev == null && n.next == null)
                    li.remove();
                else if (n.next != null)
                    li.set(n.next);
                else
                    li.set(n.prev);
            }
        }
    }

    public boolean erase(int num) {
        if (!search(num)) return false;

        LinkedList<NumberNode> vert = searchVerticalList(num);
        HashSet<NumberNode> nset = new HashSet<>();
        for (NumberNode n : vert) {
            nset.add(n);
        }
        cleanLayers(nset);
        for (NumberNode n : vert) {
            if (n.prev != null)
                n.prev.next = n.next;

            if (n.next != null)
                n.next.prev = n.prev;

            //n.down = null;
        }

        return true;
    }
}