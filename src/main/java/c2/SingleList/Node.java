package c2.SingleList;


public class Node {
    Node next;
    Character value;

    void Node(Character pvalue){
        value = pvalue;
    }

    public Character getValue(){
        return value;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node ptr){
        next = ptr;
    }


}
