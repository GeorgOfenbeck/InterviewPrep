package leetcode.p430;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

import java.util.*;

class Solution {
    public Node flatten(Node head) {
        //flattenRec(head);
        Stack<Node> stack = new Stack();


        Node ptr = head;
        Node lastPtr = ptr;
        do {
            while (ptr != null) {
                lastPtr = ptr;
                if (ptr.child != null) { // we have a sub list
                    stack.push(ptr);
                    ptr = ptr.child;
                } else {
                    ptr = ptr.next;
                }
            }
            //we finished the sub list
            if (!stack.isEmpty()) {
                Node last = lastPtr;
                ptr = stack.pop();
                Node tmp = ptr.next;
                ptr.next = ptr.child;
                ptr.next.prev = ptr;
                last.next = tmp;
                if (tmp != null)
                    tmp.prev = last;
                ptr.child = null;
            }
        } while (!stack.isEmpty());
        return head;
    }

    //returns a pointer to the last
    public Node flattenRec(Node head) {
        Node ptr = head;
        Node lastPtr = ptr;

        while (ptr != null) {
            lastPtr = ptr;
            if (ptr.child != null) { // we have a sub list
                Node last = flattenRec(ptr.child);
                Node tmp = ptr.next;
                ptr.next = ptr.child;
                ptr.next.prev = ptr;
                last.next = tmp;
                if (tmp != null)
                    tmp.prev = last;
                ptr.child = null;
            } else {
                ptr = ptr.next;
            }
        }
        return lastPtr;
    }


}