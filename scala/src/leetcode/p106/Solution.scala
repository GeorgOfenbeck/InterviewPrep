/*
package leetcode.p106


object Solution {

  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    //val (tree, _, _) = attempt(0, 0, preorder, inorder)

    val lookup = inorder.zipWithIndex.toMap // num, idx
    val (tree2, _) = second(0, preorder.length, 0, preorder, inorder, lookup)
    //    val root = TreeNode(preorder(0),)
    tree2
  }

  def second(
      leftBorder: Int,
      rightBorder: Int,
      rootidx: Int,
      preorder: Array[Int],
      inorder: Array[Int],
      lookup: Map[Int, Int],
  ): (TreeNode, Int) = {

    if (leftBorder == rightBorder)
        return (null, rootidx - 1)
    
    val root = preorder(rootidx)
    val posRoot = lookup.get(root).get



    //val leftSubTree = inorder.slice(0, posRoot)
    //val rightSubTree = inorder.slice(posRoot + 1, inorder.size)
    //al (leftPreOrder, rightPreOrder) = preorder.tail.splitAt(leftSubTree.size)

    val (left, leftidx) = second(leftBorder, posRoot, rootidx + 1, preorder, inorder, lookup)
    val (right, rightidx) = second(posRoot + 1, rightBorder, leftidx + 1, preorder, inorder, lookup)

    (TreeNode(root, left, right), rightidx) 
  }
}
  */