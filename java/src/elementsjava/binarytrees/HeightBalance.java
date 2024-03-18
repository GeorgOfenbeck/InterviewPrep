package elementsjava.binarytrees;

import datastructures.Tree;
class Ret{
    int height;
    int diff;

    Ret(int height, int diff){
        this.height = height;
        this.diff = diff;
    }
}
public class HeightBalance {



    public static boolean isBalanced(Tree tree){
        Ret ret = getTreeHeight(tree);
        if (ret.diff > 1) return false;
        else return true;
    }

    private  static Ret getTreeHeight(Tree tree){
        if (tree == null) return new Ret(0,0);

        Ret hleft = getTreeHeight(tree.left);
        if (hleft.diff > 1) return new Ret(-1,hleft.diff);

        Ret hright = getTreeHeight(tree.right);
        if (hright.diff > 1) return new Ret(-1,hright.diff);

        int diff = Math.abs (hleft.height - hright.height);
        if (diff > 1) return new Ret(-1,diff);
        else return new Ret(Math.max(hleft.height,hright.height) + 1, diff);
    }
}
