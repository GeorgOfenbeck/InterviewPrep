package c3;


import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayStack<T> {
    private int s1 = 0;
    private int s2 = 0;
    private int s3 = 0;
    private ArrayList<T> data;
    private static class StackNode<T> {




        public StackNode(){

        }
    }



    public T pop1(){
        if (s1 == 0) throw new EmptyStackException();
        T item = data.get( (s1-1) * 3);
        s1 --;
        return item;
    }

    public void push1(T item){
        s1 ++;
        data.add( (s1-1) * 3, item);
    }

    public T peek1() {
        if (s1 == 0) throw new EmptyStackException();
        T item = data.get( (s1-1) * 3);
        return item;
    }

    public boolean isEmpty1(){
        return s1 == 0;
    }

}
