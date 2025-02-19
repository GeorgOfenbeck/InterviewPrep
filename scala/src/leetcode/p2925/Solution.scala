package leetcode.p2925

import scala.collection.{mutable => mu}

case class Tree(
    val id: Int,
    var children: mu.Set[Tree],
    var value: Long,
    var subNoneZero: Long,
    var rootNoneZero: Long,
    var allZero: Long
) {
  override def toString: String = {
    s"Tree($id, $value, $subNoneZero, $rootNoneZero, $allZero, $children)"
  }
}

object Solution {
  def maximumScoreAfterOperations(
      edges: Array[Array[Int]],
      values: Array[Int]
  ): Long = {
    val tree = translate(edges, values)

    cost(tree)
    Math.max(tree.rootNoneZero, tree.subNoneZero)

  }

  def cost(tree: Tree): Unit = {

    // withRoot == we are setting root to 0 (and count its value)
    if (tree.children.isEmpty) {
      tree.allZero = tree.value
      tree.rootNoneZero = 0
      tree.subNoneZero = 0
      return
    }

    // update for each child
    tree.children.foreach { child =>
      cost(child)
    }

    // value of taking the full subtree + root
    tree.allZero = tree.value + tree.children.toVector.map(_.allZero).sum

    // value of not taking the root
    tree.rootNoneZero = tree.children.toVector.map(_.allZero).sum

    // value of taking the root with nonZero subtrees

    tree.subNoneZero = tree.value + tree.children.toVector
      .map(child => {
        // child root nonzero
        Math
          .max(
            child.rootNoneZero,
            // child subtrees nonZero
            child.subNoneZero
          )
      })
      .sum
  }

  def updateEdgeMap(
      edgeMap: mu.Map[Int, Set[Int]],
      to: Int,
      from: Int
  ): Unit = {
    edgeMap.updateWith(from)(valueopt =>
      valueopt match {
        case None        => Some(Set(to))
        case Some(value) => Some(value + to)
      }
    )
  }

  def translate(edges: Array[Array[Int]], values: Array[Int]): Tree = {
    val nodemap: mu.Map[Int, Tree] = mu.Map.empty

    val edgeMap: mu.Map[Int, Set[Int]] = mu.Map.empty

    for (edge <- edges) {
      val (from, to) = (edge(0), edge(1))
      updateEdgeMap(edgeMap, from, to)
      updateEdgeMap(edgeMap, to, from)
    }

    constructTree(edgeMap, Vector(0), nodemap)
    nodemap.keys.foreach { key =>
      nodemap.updateWith(key)(valueopt =>
        valueopt match {
          case Some(tree) =>
            tree.value = values(key)
            Some(tree)
          case None => throw new RuntimeException("should not happen")
        }
      )
    }
    nodemap(0)
  }

//TODO: fifo

  def constructTree(
      edgeMap: mu.Map[Int, Set[Int]],
      todo: Vector[Int],
      nodemap: mu.Map[Int, Tree]
  ): Unit = {
    if (todo.isEmpty) return

    val cur = todo.head
    val children = edgeMap(cur)

    // remove parent from children
    children.foreach { child =>
      edgeMap.updateWith(child)(valueopt => valueopt.map(set => set - cur))
    }

    // add children to todo
    val newtodo = todo.tail ++ children

    nodemap.updateWith(cur)(valueopt =>
      valueopt match {
        case None =>
          val tree = Tree(cur, mu.Set.empty, 0, 0, 0, 0)
          children.foreach { childid =>
            val child = Tree(childid, mu.Set.empty, 0, 0, 0, 0)
            tree.children.add(child)
            nodemap.update(childid, child)
          }
          Some(tree)
        case Some(tree) =>
          children.foreach { childid =>
            val child = Tree(childid, mu.Set.empty, 0, 0, 0, 0)
            tree.children.add(child)
            nodemap.update(childid, child)
          }
          Some(tree)
      }
    )

    constructTree(edgeMap, newtodo, nodemap)
  }

  @main
  def test(): Unit = {
    // test1()
    test3()
  }

  def test2(): Unit = {
    // edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [20,10,9,7,4,3,5]
    println(
      maximumScoreAfterOperations(
        Array(
          Array(0, 1),
          Array(0, 2)
        ),
        Array(1, 1, 1)
      )
    )
  }

  def test1(): Unit = {
    // edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [20,10,9,7,4,3,5]
    println(
      maximumScoreAfterOperations(
        Array(
          Array(0, 1),
          Array(0, 2),
          Array(1, 3),
          Array(1, 4),
          Array(2, 5),
          Array(2, 6)
        ),
        Array(20, 10, 9, 7, 4, 3, 5)
      )
    )
  }

  def test3(): Unit = {
    /*edges =
[[7,0],[3,1],[6,2],[4,3],[4,5],[4,6],[4,7]]
values =
[2,16,23,17,22,21,8,6]
     */
    println(
      maximumScoreAfterOperations(
        Array(
          Array(7, 0),
          Array(3, 1),
          Array(6, 2),
          Array(4, 3),
          Array(4, 5),
          Array(4, 6),
          Array(4, 7)
        ),
        Array(2, 16, 23, 17, 22, 21, 8, 6)
      )
    )
  }
}
