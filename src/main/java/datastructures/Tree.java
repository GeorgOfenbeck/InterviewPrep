package datastructures;

public class Tree{
    public Tree left;
    public Tree right;

    public Tree parent;
    public int value;

    Tree(Tree left, Tree right, Tree parent, int value){
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.value = value;
    }
}
