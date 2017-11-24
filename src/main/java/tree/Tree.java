package tree;

import java.util.*;

class Tree{
  Tree left = null;
  Tree right = null;
  Tree parent = null;
  Integer value;

  Tree(Tree pparent, Integer in){
    value = in;
    parent = pparent;
  }

  public static void main (String[] args){
    TreeSet<Integer> ts = new TreeSet<>();
    
  }


  void insert(Integer in){
    if (in <= value){ //not dealing with == atm
      if (left == null)
        left = new Tree(this,in);
      else
        left.insert(in);
    }
    else {
      if (right == null)
        right = new Tree(this,in);
      else
        right.insert(in);
    }
  } //end insert

  Tree find(Tree pos, Integer v){
    if (pos == null) return null;
    if (pos.value == v) return pos;
    else {
      if (pos.value < v) return find(pos.left,v);
      else return find(pos.right,v);
    }
  } //tree find

  Tree findMax(Tree pos){
    if (pos.right == null) return this;
    else
     return findMax(pos.right);
  }

  Tree findMin(Tree pos){
    if (pos.left == null) return this;
    else return findMin(pos.right);
  }

  void delete(Tree pos){
    Tree parent = pos.parent;
    if (parent.left == pos) // we are left child
    {
      parent.left = this.left;
    }

    else
    {
      parent.right = this.left;
    }
    Tree max = findMax(this.left);
    max.right = this.right;
    this.right.parent = max.right;
    this.left.parent = parent;
  }


  Tree findSucc(){
    if (right != null) return findMin(right);
    else {
      Tree p = this;
      while (p.parent != null && p.left != this) {
        p = p.parent;
      }
      return p;
    }
    }// end find succ

    Tree findPred(){
      if (left != null) return findMax(left);
      else {
        Tree p = this;
        while (p.parent != null && p.right != this) {
          p = p.parent;
        }
        return p;
      }
      }// end find succ



    }
