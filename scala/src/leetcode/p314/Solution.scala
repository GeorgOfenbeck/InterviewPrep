package leetcode.p314

import scala.collection.immutable.TreeMap
/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = -1, _left: TreeNode = null, _right: TreeNode = null) {
 *   var value: Int = _value
 *   var left: TreeNode = _left
 *   var right: TreeNode = _right
 * }
 */
object Solution {
    def verticalOrder(root: TreeNode): List[List[Int]] = {
        val xymap = recurse(root, TreeMap(), 0, 0)

        xymap.foldLeft(List[List[Int]]())((acc, entry) => {
            val (col, rowMap) = entry
            
            val rowConcat = rowMap.values.flatten.toList

            val result: List[List[Int]] =  rowConcat +: acc 

            result.reverse
        })

    }

    def recurse(node: TreeNode, map: TreeMap[Int, TreeMap[Int,List[Int]]], column: Int, row: Int): TreeMap[Int, TreeMap[Int,List[Int]]] = {
        if (node == null) {
            return map
        }
        val thisColumn: TreeMap[Int,List[Int]] = map.getOrElse(column, TreeMap())

        val thisRow = thisColumn.getOrElse(row, List())

        val updated = map + (column -> (thisColumn + (row -> (node.value :: thisRow))))

        val right = recurse(node.right, updated, column + 1, row + 1)
        val left = recurse(node.left, right, column - 1, row + 1)

        left
    }


}