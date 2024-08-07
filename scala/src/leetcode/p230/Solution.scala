package leetcode.p230

import scala.util.Either
import scala.QuickTest.t



class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
    def kthSmallest(root: TreeNode, k: Int): Int = {
        rec(root,k ,0) match {
            case Left(value) => {
                println("that should not happen")
                return value
            }
            case Right(value) => {
                return value
            }
        }
    }

    def rec(node: TreeNode, k: Int, sofar: Int): Either[Int, Int] = {
        if (node == null)
            return Left(sofar): Either[Int, Int]
        //check in left subtree - if its part of it return it (right side) - otherwise return #ele
        val left = rec(node.left, k, sofar )
        //check if its us?
        left match{
            case Left(value) => {
                    if (value + 1 == k) //its us
                    {
                        return Right(node.value) : Either[Int,Int]
                    } else {
                        return rec(node.right, k, value + 1)
                    }
            }
            case Right(value) => return left 
        }
    }
}