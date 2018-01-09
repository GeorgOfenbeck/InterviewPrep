package geeksforgeeks.bintreeconect;
/*
Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function would be added by GfG's Online Judge.*/

import java.util.*;

// A Binary Tree node
class Node
{
    int data;
    Node left, right, nextRight;
    Node(int item)
    {
        data = item;
        left = right = nextRight = null;

    }
}


class GfG
{
  void connect(Node root)
  {
    if (root == null) return;
    Stack<Node> stack  = new Stack<>();
    stack.add(root);
    recurse(stack);
  }

  void recurse(Stack<Node> todo){
    //buffer for the new stack
    Stack<Node> nextlevel = new Stack<>();
    //pop the stack and connect
    Node left = null;
    while (!todo.isEmpty()){
      Node cur = todo.pop();
      if (left != null)
        left.nextRight = cur;
      if (cur.right != null)
        nextlevel.push(cur.right);
      if(cur.left != null)
        nextlevel.push(cur.left);
    }
    if (!nextlevel.isEmpty()){
      recurse(nextlevel);
    }
  }
}