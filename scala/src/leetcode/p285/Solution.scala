package leetcode.p285

import scala.annotation.tailrec

class TreeNode(var value: Int) {
  var value: Int = value
  var left: TreeNode = null
  var right: TreeNode = null
}

object Solution {
  def inorderSuccessor(root: TreeNode, p: TreeNode): TreeNode = {
    find(root, p)
  }

  def find(root: TreeNode, p: TreeNode): TreeNode = {

    // we have a right branch - therefore its the leftmost node of that
    if (p.right != null) {
      var leftmost = p.right
      while (leftmost.left != null)
        leftmost = leftmost.left
      return leftmost
    }

    //println ("searching for node")
    var parents = List.empty[TreeNode]

    var ptr = root
    while (ptr.value != p.value) {
      if (p.value < ptr.value) {
        parents = ptr +: parents
        ptr = ptr.left
      } else if (p.value > ptr.value) {
        parents = ptr +: parents
        ptr = ptr.right
      }
    }
/* 
    for (p <- parents)
        println(p.value)
    
    println(s" ${p.value} ${ptr.value}") */
    
    while (!parents.isEmpty) {
      val cur = parents.head
      if (ptr == cur.left)
        return cur
      else {
        parents = parents.tail
        ptr = cur
      }
    }

    return null

  }
}
