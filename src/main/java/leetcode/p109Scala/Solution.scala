package leetcode.p109Scala

import leetcode.p109.ListNode

/**
  * Definition for singly-linked list.
  *
  */
/**
  * Definition for a binary tree node.
  * class TreeNode(var _value: Int) {
  * var value: Int = _value
  * var left: TreeNode = null
  * var right: TreeNode = null
  * }
  */

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null
}

object Solution {

  def main(args: Array[String]): Unit = {
    val dummy = new ListNode(-1)
    val list = (0 until 10).foldLeft(dummy)((acc, ele) => {
      val cur = new ListNode(ele)
      acc.next = cur
      cur
    }
    )
    sortedListToBST(dummy.next)

  }

  def sortedListToBST(head: ListNode): TreeNode = {
    if (head == null) null else {
      val size = getSize(head); //O(n)
      val height = getTreeHeight(size);
      val (tree, remain, nrNodes) = treeifyInOrder(head, 0, height, size)
      tree
    }
  }


  private def treeifyInOrder(list: ListNode, depth: Int, height: Int, nrNodes: Int): (TreeNode, ListNode, Int) = {
    if (nrNodes == 0) (null, list, nrNodes)
    else {
      if (depth == height || nrNodes == 1) {
        val cur: TreeNode = new TreeNode(list.x)
        (cur, list.next, nrNodes-1)
      }
      else {
        //nrNodes == 2
        val leftNodes = (nrNodes - 1) / 2 //0
        val rightNodes = nrNodes - 1 - leftNodes //1
        val (leftTree, listAfterLeft, nrNodesAfterLeft) = treeifyInOrder(list, depth + 1, height,leftNodes)
        val cur: TreeNode = new TreeNode(listAfterLeft.x)
        val (rightTree, listAfterRight, nrNodesAferRight) = treeifyInOrder(listAfterLeft.next, depth + 1, height,rightNodes)
        cur.left = leftTree
        cur.right = rightTree
        (cur, listAfterRight, 0)
      }
    }
  }

  private def getTreeHeight(i: Int): Int = {
    var height = 0
    while ((Math.pow(2, height + 1).toInt - 1) < i)
      height = height + 1
    height
  }

  private def getSize(head: ListNode): Int = {
    var ptr: ListNode = head
    var i: Int = 0
    while (ptr != null) {
      ptr = ptr.next
      i = i + 1
    }
    i
  }
}