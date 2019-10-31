package leetcode.p1206;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;


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
