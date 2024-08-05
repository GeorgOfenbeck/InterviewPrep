package leetcode.p105

class TreeNode(
    _value: Int = 0,
    _left: TreeNode = null,
    _right: TreeNode = null
) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

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
        return null
    
    val root = preorder(leftBorder)
    val posRoot = lookup.get(root).get



    //val leftSubTree = inorder.slice(0, posRoot)
    //val rightSubTree = inorder.slice(posRoot + 1, inorder.size)
    //al (leftPreOrder, rightPreOrder) = preorder.tail.splitAt(leftSubTree.size)

    val (left, leftidx) = second(leftBorder, posRoot, rootidx + 1, preorder, inorder, lookup)
    val (right, rightidx) = second(posRoot + 1, leftidx + 1, rightBorder, preorder, inorder, lookup)

    (TreeNode(root, left, right), rightidx) 
  }


  @main
  def main() = {
    val expected = TreeNode(
      3,
      TreeNode(
        8,
        TreeNode(
          9,
          null,
          TreeNode(7, TreeNode(1, null, null), TreeNode(2, null, null))
        ),
        null
      ),
      TreeNode(5, null, null)
    )

    val res = buildTree(
      Array(1, 2, 3),
      Array(2, 3, 1)
    )

    // val res = buildTree(
    //  Array(3, 8, 9, 7, 1, 2, 5),
    //  Array(9, 1, 7, 2, 8, 3, 5)
    // )
    println(res)
  }


  def attempt(
      pptr: Int,
      iptr: Int,
      preorder: Array[Int],
      inorder: Array[Int]
  ): (TreeNode, Int, Int) = {

    if (pptr == preorder.size) {
      (null, pptr, iptr)
    } else {
      val root = preorder(pptr)

      val (leftTree, lp, li) = if (root != inorder(iptr)) {
        // we are not yet on the very left
        attempt(pptr + 1, iptr, preorder, inorder)
      } else {
        (null, pptr, iptr)
      }

      val (rightTree, rp, ri) =
        if (li + 1 == inorder.size) // we are at the rightmost node
          (null, lp, li)
        else if (lp == 0) {
          attempt(li + 1, li + 1, preorder, inorder)
        } else if (preorder(lp - 1) == inorder(li + 1)) { // we are not at the root
          (null, lp - 1, li + 1) // there is no right side - ptrs to the parent
        } else {
          attempt(li + 1, li + 1, preorder, inorder)
        }

      val tree = TreeNode(root, leftTree, rightTree)
      (tree, rp, ri)

    }

  }

}
