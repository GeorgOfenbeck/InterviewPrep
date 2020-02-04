package algorithms;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriQueTest {

    public static void main( String [] args){
        PriorityQueue<Integer> pQueu2 = new PriorityQueue<>( (x,y) -> y - x);
        PriorityQueue<Integer> pQueu = new PriorityQueue<>( (x,y) -> y - x);
        pQueu.add(50);
        pQueu.add(100);



        pQueu2.add(90);

        pQueu.addAll(pQueu2);
        Integer head = pQueu.remove();
        System.out.println(head);
        head = pQueu2.remove();
        System.out.println(head);
    }


}
