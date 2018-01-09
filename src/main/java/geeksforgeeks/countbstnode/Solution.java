package geeksforgeeks.countbstnode;


class Node
{
  int data;
  Node left, right;
  Node(int item)
  {
    data = item;
    left = right = null;
  }
public class Solution {


  int getCountOfNode(Node root, int low, int high)
  {
    int total = getSize(root);
    int higher = getHigher(null,root,high,0);
    int lower = getLower(null,root,low,0);
    int interval = total - higher - lower;
    return interval;
  }

  int getHigher(Node parent, Node node, int high, int sofar){
    if (node ==null ) return sofar;
    if (node.data == high)
      return sofar + getSize(node.right);
    if (node.data < high){
      return getHigher(node,node.right,high, sofar);
    } else {
      return getHigher(node,node.left,high, sofar + getSize(node.right) +1);
    }
  }

  int getLower(Node parent, Node node, int low, int sofar){
    if (node ==null ) return sofar;
    if (node.data == low)
      return sofar + getSize(node.left);
    if (node.data > low){
      return getLower(node,node.left,low, sofar);
    } else {
      return getLower(node,node.right,low, sofar + getSize(node.left) +1);
    }
  }

  int getSize(Node root){
    if (root == null) return 0;
    return getSize(root.left) + getSize(root.right) + 1;
  }
}

}

