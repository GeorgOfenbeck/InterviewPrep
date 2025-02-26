package leetcode.p834

import scala.collection.{mutable => mu}

object Solution {

  case class Tree(
      id: Int,
      children: Vector[Tree],
      var nrNodes: Int,
      var sumToHere: Int
  )

  def sumOfDistancesInTree(n: Int, edges: Array[Array[Int]]): Array[Int] = {
    if (n == 1) return Array(0)
    val (root, parentMap) = createTree(edges = edges)
    updateTree(root)
    val resMap = mu.Map.empty[Int, Int]
    calcSumDistance(root, null, resMap, n)
    resMap.addOne(root.id, root.sumToHere)
    val res: Array[Int] = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      res(i) = resMap.getOrElse(i, 0)
    }
    res
  }

  def calcSumDistance(
      cur: Tree,
      parent: Tree,
      resMap: mu.Map[Int, Int],
      n: Int
  ): Unit = {
    cur.children.foreach { child =>
      {
        child.sumToHere = cur.sumToHere - child.nrNodes + n - child.nrNodes
        resMap.addOne(child.id, child.sumToHere)
        calcSumDistance(child, cur, resMap, n)
      }
    }
  }

  // return sumToTop, #Nodes
  def updateTree(cur: Tree): Unit = {
    cur.children.foreach(child => updateTree(child))
    if (cur.children.isEmpty) {
      cur.sumToHere = 0
      cur.nrNodes = 1
    } else {
      cur.nrNodes = cur.children.map(_.nrNodes).sum + 1
      cur.sumToHere =
        cur.children.map(child => child.sumToHere + child.nrNodes).sum
    }
  }

  def createTree(edges: Array[Array[Int]]): (Tree, Map[Int, Int]) = {
    val edgeMap = mu.Map.empty[Int, Vector[Int]]
    val parents = mu.Set.empty[Int]
    val children = mu.Set.empty[Int]
    val parentMap = mu.Map.empty[Int, Int]

    for (edge <- edges) {
      val (from, to) = (edge(0), edge(1))
      edgeMap.updateWith(from)(valopt =>
        valopt match {
          case Some(tos) => Some(tos :+ to)
          case None      => Some(Vector(to))
        }
      )

      edgeMap.updateWith(to)(valopt =>
        valopt match {
          case Some(tos) => Some(tos :+ from)
          case None      => Some(Vector(from))
        }
      )
      parentMap.addOne((to, from))
      parents.add(from)
      children.add(to)
    }

    (createTreeNode(0, edgeMap, -1), parentMap.toMap)
  }

  def createTreeNode(
      cur: Int,
      grouped: mu.Map[Int, Vector[Int]],
      parent: Int
  ): Tree = {
    if (grouped.get(cur).isEmpty) {
      return Tree(cur, Vector.empty, 0, 0)
    }
    val children: Vector[Tree] =
      grouped(cur)
        .filter(_ != parent)
        .map(childid => createTreeNode(childid, grouped, cur))
    Tree(cur, children, 0, 0)
  }

  @main
  def test(): Unit = {
    val n = 6
    val edges =
      Array(Array(0, 1), Array(0, 2), Array(2, 3), Array(2, 4), Array(2, 5))
    val res = sumOfDistancesInTree(n, edges)
    println(res.mkString(","))
  }

  @main
  def test3(): Unit = {
    val n = 2
    val edges = Array(Array(1, 0))
    val res = sumOfDistancesInTree(n, edges)
    println(res.mkString(","))
  }

}
