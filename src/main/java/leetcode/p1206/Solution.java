package leetcode.p1206;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;


class Skiplist {

    public static void main(String[] args){
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
        System.out.println( skiplist.erase(0));    // return false, 0 is not in skiplist.
        skiplist.printSkipFullList();
        System.out.println(skiplist.erase(1) == true);    // return true.
        skiplist.printSkipFullList();
        System.out.println(skiplist.search(1));   // return false, 1 has already been erased.)
        skiplist.printSkipFullList();
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


    LinkedList<NumberNode> layers = new LinkedList<>();
    // 0 bottom layer -> n top most layer

    public void printRow(NumberNode p){
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

    public void printSkipFullList(){
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
        while (cur.next != null && cur.value != target && cur.next.value < target)
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
        while (cur.prev != null && cur.value != target && cur.prev.value > target)
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
        NumberNode top = layers.getFirst();
        NumberNode layerresult = searchHorizontal_forward(top, target);

        ret.add(layerresult);
        while (layerresult.down != null) {
            if (layerresult.down.value < target)
                layerresult = searchHorizontal_backward(layerresult.down, target);
            else
                layerresult = searchHorizontal_forward(layerresult.down, target);
            ret.add(layerresult);
        }
        return ret;
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
            NumberNode down = null;
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
                        nu.prev.next = neigh;
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
                while(gen.nextBoolean()){
                    NumberNode nu = new NumberNode(num);
                    nu.down = prev;
                    layers.add(nu);
                    prev = nu;
                }
        }
    }

    private void cleanLayers(int num){
        ListIterator<NumberNode> li = layers.listIterator();
        while (li.hasNext()) {
            NumberNode n = li.next();
            if (n.value == num) {
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

        for (NumberNode n : vert){
            if (n.prev != null){
                n.prev.next = n.next;
            if (n.next != null)
                n.next.prev = n.prev;
            }
            n.down = null;
        }
        cleanLayers(num);
        return true;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */


class Skiplist_old {
    Random gen = new Random();

    class NumberNode {
        NumberNode next = null;
        NumberNode prev = null;
        NumberNode down = null;
        int value;

        NumberNode(int value) {
            this.value = value;
        }
    }


    LinkedList<NumberNode> layers;

    public Skiplist_old() {
        layers = new LinkedList<>();
    }

    //stop at value or smaller then value
    private NumberNode searchHorizontal(NumberNode layer, int target) {
        NumberNode cur = layer;
        while (cur.next != null && cur.value != target && cur.next.value > target)
            cur = cur.next;
        return cur;
    }

    private NumberNode searchHorizontalBack(NumberNode layer, int target) {
        NumberNode cur = layer;
        while (cur.prev != null && cur.value != target && cur.prev.value < target)
            cur = cur.prev;
        return cur;
    }


    //go right as long as small -> then go down and repeat
    private NumberNode searchVertical(int target) {
        NumberNode top = layers.getFirst();
        NumberNode layerresult = searchHorizontal(top, target);
        while (layerresult.down != null && layerresult.value != target) {
            layerresult = searchHorizontal(layerresult.down, target);
        }
        return layerresult;
    }

    //bottom -> top
    private LinkedList<NumberNode> searchVerticalList(int target) {
        LinkedList<NumberNode> ret = new LinkedList<>();
        NumberNode top = layers.getFirst();


        NumberNode layerresult = searchHorizontal(top, target);
        ret.add(layerresult);
        while (layerresult.down != null && layerresult.value != target) {
            layerresult = searchHorizontal(layerresult.down, target);
            ret.add(layerresult);
        }
        return ret;
    }

    public boolean search(int target) {
        NumberNode layerresult = searchVertical(target);
        if (layerresult.value == target) return true;
        else return false;
    }

    private NumberNode searchlowest(int target) {
        NumberNode layerresult = searchVertical(target);
        if (layerresult.value == target) {
            while (layerresult.down != null)
                layerresult = layerresult.down;
            return layerresult;
        } else {
            return layerresult; //should already be lowest layer
        }
    }

    public void add(int num) {
        if (layers.isEmpty()) {
            NumberNode n = new NumberNode(num);
            layers.addFirst(n);
            NumberNode down = n;
            while (gen.nextBoolean()) {
                NumberNode nu = new NumberNode(num);
                layers.addFirst(nu);
                nu.down = down;
                down = nu;
            }
        } else {
            LinkedList<NumberNode> vertlist = searchVerticalList(num);
            Iterator<NumberNode> it = vertlist.iterator();
            NumberNode bottom = it.next();

            NumberNode nu = new NumberNode(num);
            nu.next = bottom.next;
            bottom.next = nu;
            NumberNode prev = nu;

            while (gen.nextBoolean() && it.hasNext()) {
                nu = new NumberNode(num);
                bottom = it.next();
                nu.next = bottom.next;
                bottom.next = nu;
                nu.down = prev;
            }
            if (!it.hasNext()) { //we ran out of layers put still rolling true's
                do {
                    nu = new NumberNode(num);
                    layers.addFirst(nu);
                    nu.down = prev;
                    prev = nu;
                } while (gen.nextBoolean());
            }
        }
    }

    public boolean erase(int num) {
        if (!search(num)) return false;
        else {
            LinkedList<NumberNode> vertlist = searchVerticalList(num);
            Iterator<NumberNode> layerit = layers.iterator();
            Iterator<NumberNode> vertit = vertlist.iterator();

            assert (layers.size() == vertlist.size());

            while (layerit.hasNext() && vertit.hasNext()) {
                NumberNode layerHead = layerit.next();
                NumberNode eleInLayer = vertit.next();

                if (layerHead == eleInLayer) {
                    if (layerHead.next == null)
                        layerit.remove();
                    else
                        layerHead.next = layerHead.next.next;
                    layerHead.down = layerHead.next.down;
                    layerHead.prev = null;
                    layerHead.value = layerHead.next.value;
                } else {
                    if (eleInLayer.value == num) {
                        if (eleInLayer.prev != null)
                            eleInLayer.prev.next = eleInLayer.next;
                        if (eleInLayer.next != null)
                            eleInLayer.next.prev = eleInLayer.prev;
                    }
                }
            }
            return true;
        }
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
