/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */

package leetcode.p2313

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def minimumFlips(root: TreeNode, result: Boolean): Int = {

    // 0 -> false
    // 1 -> true
    // OR, AND, XOR, NOT

    special(root, result)
  }

  @tailrec
  def special(root: TreeNode, result: Boolean): Int = {
    if (root.value == 5) {
      if (root.left != null) {
        special(root.left, !result)
      } else {
        special(root.right, !result)
      }
    } else {
      miniFlips(
        root,
        result,
        List.empty,
        mu.Map.empty[(List[Boolean], Boolean), Int]
      )
    }
  }

  def miniFlips(
      root: TreeNode,
      result: Boolean,
      path: List[Boolean],
      cache: mu.Map[(List[Boolean], Boolean), Int]
  ): Int = {
    val tup = (path, result)
    if (cache.contains(tup)) {
      return cache(tup)
    }

    val lefto = if (root.left == null) None else Some(root.left)
    val righto = if (root.right == null) None else Some(root.right)

    val nrFlips = (lefto, righto) match {
      case (None, None) => {
        // we are a leaf
        val rootbool = root.value == 1
        if (rootbool == result) {
          0
        } else {
          1
        }
      }
      case (Some(left), None) => {
        root.value match {
          case 5 => {
            return miniFlips(left, !result, true +: path, cache)
          }
          case _ => assert(false)
        }
      }
      case (None, Some(right)) => {
        root.value match {
          case 5 => {
            return miniFlips(right, !result, false +: path, cache)
          }
          case _ => assert(false)
        }
      }
      case (Some(left), Some(right)) => {
        root.value match {
          case 2 => {
            // or
            if (result == false) {
              miniFlips(left, false, true +: path, cache) + miniFlips(
                right,
                false,
                false +: path,
                cache
              )
            } else {
              Math.min(
                miniFlips(left, true, path.prepended(true), cache),
                miniFlips(right, true, path.prepended(false), cache)
              )
            }
          }
          case 3 => {
            // and
            if (result == false) {
              Math.min(
                miniFlips(left, false, path.prepended(true), cache),
                miniFlips(right, false, path.prepended(false), cache)
              )
            } else {
              miniFlips(left, true, path.prepended(true), cache) + miniFlips(
                right,
                true,
                path.prepended(false),
                cache
              )
            }
          }
          case 4 => {
            // xor
            if (result == false) {
              val bothfalse =
                miniFlips(left, false, path.prepended(true), cache) + miniFlips(
                  right,
                  false,
                  path.prepended(false),
                  cache
                )
              val bothtrue =
                miniFlips(left, true, path.prepended(true), cache) + miniFlips(
                  right,
                  true,
                  path.prepended(false),
                  cache
                )
              Math.min(bothfalse, bothtrue)
            } else {

              val lefttrue =
                miniFlips(left, true, path.prepended(true), cache) + miniFlips(
                  right,
                  false,
                  path.prepended(false),
                  cache
                )
              val righttrue =
                miniFlips(left, false, path.prepended(true), cache) + miniFlips(
                  right,
                  true,
                  path.prepended(false),
                  cache
                )
              Math.min(lefttrue, righttrue)
            }
          }
          case _ => assert(false)
        }
      }
    }
    cache(tup) = nrFlips
    nrFlips
  }

  @main
  def test(): Unit = {
    val tree = TreeNode(
      4,
      TreeNode(5, TreeNode(2, TreeNode(0), TreeNode(0))),
      TreeNode(0)
    )
    println(minimumFlips(tree, false))
  }

  @main
  def test2(): Unit = {
    // [4,0,2,null,null,0,5,null,null,null,5,null,1,null,null]
    val tree = TreeNode(
      4,
      TreeNode(0),
      TreeNode(2, TreeNode(0), TreeNode(5, TreeNode(5, TreeNode(1))))
    )
    println(minimumFlips(tree, true))
  }

}
