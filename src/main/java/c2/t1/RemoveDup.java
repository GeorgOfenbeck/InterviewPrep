package c2.t1;


import c2.SingleList.Node;

import java.util.HashSet;

public class RemoveDup {

    public static void removedup(Node head){
        HashSet<Character> set = new HashSet<Character>();

        Node ptr = head;

        while (ptr != null)
        {
            if(set.contains(ptr.getValue()))
                ptr.setNext(ptr.getNext());

        }

    }

    public static void removedup2(Node head){
        //o2
    }
}
