package leetcode.p310

import scala.annotation.tailrec
import scala.collection._

object Solution_Scala extends App {

  //val n = 7
  //val edges =         Array(Array(0,1),Array(1,2),Array(1,3),Array(2,4),Array(3,5),Array(4,6))
  val n = 4
  val edges = Array(Array(1, 0), Array(1, 2), Array(1, 3))

  println(findMinHeightTrees(n, edges))


  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    if (n == 1 || edges.isEmpty)
      List(0)
    else {
      val (edgeMap, rootSet) = edges.foldLeft((mutable.Map.empty[Int, Set[Int]], Set.empty[Int]))(
        (acc, ele) => {
          val (edgeMap, rootSet) = acc
          val from = ele(0)
          val to = ele(1)
          edgeMap += (from -> (edgeMap.getOrElse(from, Set.empty[Int]) + to))
          edgeMap += (to -> (edgeMap.getOrElse(to, Set.empty[Int]) + from))
          val fromSet = if (edgeMap(from).size == 1) rootSet + from else rootSet - from
          val toSet = if (edgeMap(to).size == 1) fromSet + to else fromSet - to
          (edgeMap, toSet)
        }
      )
      fill_m(edgeMap, rootSet).toList
    }
  }


  def removeNode_m(edges: mutable.Map[Int, Set[Int]], node: Int, from: Set[Int]): (mutable.Map[Int, Set[Int]], Set[Int]) = {
    from.foldLeft((edges, Set.empty[Int]))((acc, ele) => {
      val (nodes, roots) = acc
      val nSet: Set[Int] = nodes(ele) - node
      val nRoots: Set[Int] = if (nSet.size <= 1) roots + ele else roots - ele
      nodes += (ele -> nSet)
      (nodes, nRoots)
    })
  }

  def fill_m(edges: mutable.Map[Int, Set[Int]], todo: Set[Int]): Set[Int] = {
    var nRoots = Set.empty[Int]
    todo.map( root => {
      val neigh = edges(root)
      //val xx = neigh -- todo
      val (m,s) = removeNode_m(edges,root,neigh)
      nRoots = nRoots ++ s
    })
    nRoots = nRoots -- todo
    //val nRoots = todo.map(root => removeNode_m(edges,root,nexts)._2).flatten
    val nMap = edges
    if (nRoots.isEmpty) todo
    else {
      todo.map( root => nMap.remove(root))
      fill_m(nMap, nRoots)
    }
  }



  def findMinHeightTrees_im(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val (edgeMap, rootSet) = edges.foldLeft((Map.empty[Int, Set[Int]], Set.empty[Int]))(
      (acc, ele) => {
        val (edgeMap, rootSet) = acc
        val from = ele(0)
        val to = ele(1)
        val nMap = edgeMap +
          (from -> (edgeMap.getOrElse(from, Set.empty[Int]) + to)) +
          (to -> (edgeMap.getOrElse(to, Set.empty[Int]) + from))
        val fromSet = if (nMap(from).size == 1) rootSet + from else rootSet - from
        val toSet = if (nMap(to).size == 1) fromSet + to else fromSet - to
        (nMap, toSet)
      }
    )
    fill(edgeMap, rootSet).toList
  }

  def removeNode(edges: Map[Int, Set[Int]], node: Int, from: Set[Int]): (Map[Int, Set[Int]], Set[Int]) = {
    from.foldLeft((edges, Set.empty[Int]))((acc, ele) => {
      val (nodes, roots) = acc
      val nSet: Set[Int] = nodes(ele) - node
      val nRoots: Set[Int] = if (nSet.size <= 1) roots + ele else roots - ele
      val nMap: Map[Int, Set[Int]] = nodes + (ele -> nSet)
      (nMap, nRoots)
    })
  }

  def fill(edges: Map[Int, Set[Int]], todo: Set[Int]): Set[Int] = {
    val (nMap, nRoots) = todo.foldLeft((edges, Set.empty[Int]))(
      (acc, ele) => {
        val (nodes, roots) = acc
        val next = edges(ele) -- todo //get every next node, ignore those that are roots right now
        val (nMap, nRoots) = removeNode(nodes, ele, next) //remove ourselfs from the next
        (nMap, roots ++ nRoots)
      }
    )
    if (nRoots.isEmpty) todo
    else {
      val removeTodo = nMap.foldLeft(Map.empty[Int, Set[Int]])((acc, ele) => {
        val (k, v) = ele
        if (todo.contains(k))
          acc
        else
          acc + (k -> v)
      })
      fill(removeTodo, nRoots)
    }
  }
}

/*
























case class Node(id: Int) {
  var neigh: Set[Int] = Set.empty
  var depths: Map[Int, Int] = Map.empty
}

def findMinHeightTrees2(n: Int, edges: Array[Array[Int]]): List[Int] = {
  if (n == 1 || edges.isEmpty)
    List(0)
  else {
    val nodes = (0 until n).map(r => r -> Node(r)).toMap
    //add neighbour information
    edges.map(edge => {
      val from = edge(0)
      val to = edge(1)
      nodes(from).neigh = nodes(from).neigh + to
      nodes(to).neigh = nodes(to).neigh + from
    })

    val roots: Set[Int] = nodes.map { case (id, node) => {
      if (node.neigh.size == 1) {
        val tt: Option[Int] = Some(node.id)
        tt
      }
      else
        None
    }
    }.flatten.toSet

    //      getHeight(nodes, roots, Map.empty, roots).toList
  }
}

def merge[K](m1: Map[K, Int], m2: Map[K, Int]): Map[K, Int] =
  (m1.keySet ++ m2.keySet) map { i => {

    val zero: Int = 0
    val t1: Int = m1.getOrElse[Int](i, zero)
    val t2: Int = m2.getOrElse[Int](i, zero)
    val t: Int = t1 + t2
    (i, t): (K, Int)
  }
  } toMap


def water(nodes: Map[Int, Node], visited: Map[Int, Int], todo: Set[Int], done: Set[Int]): Set[Int] = {
  var ndone = Set.empty[Int]
  val before_reduce = todo.toVector.map(nodeid => {
    val newneigh: Set[Int] = nodes(nodeid).neigh -- done
    newneigh.map(e => e -> 1).toMap


  })
  val next = before_reduce.reduce((a, b) => merge(a, b))
  //Map of Nodes that are neighbouring and how many nodes want to visit them

  var nvisited = visited
  var nearfull = Set.empty[Int]

  next.map { case (id, arrivals) => {
    nvisited = nvisited + (id -> (nvisited.getOrElse(id, 0) + arrivals))
    if (nvisited(id) == nodes(id).neigh.size - 1)
      nearfull = nearfull + id
    if (nvisited(id) == nodes(id).neigh.size == 0)
      ndone = ndone + id
  }
  }
  if (nearfull.isEmpty) //we don't have somewhere to continue
    if (!ndone.isEmpty) //is it because we closed the graph from multiple sides?
      ndone
    else //or is it because we are done with graph
      todo
  else
    water(nodes, nvisited, nearfull, done ++ todo)
}


}

@tailrec
def getHeight (nodes: Map[Int, Node], processed: Set[Int], visited: Map[Int, Int], todo: Set[Int] ): Set[Int] = {
val before_reduce = todo.toVector.map (nodeid => nodes (nodeid).neigh.map (e => e -> 1).toMap)
val next = before_reduce.reduce ((a, b) => merge (a, b) )

var nvisited = visited
var nearfull = Set.empty[Int]
var done = Set.empty[Int]
next.map {
case (id, arrivals) => {
nvisited = nvisited + (id -> (nvisited.getOrElse (id, 0) + arrivals) )
if (nvisited (id) == nodes (id).neigh.size - 1)
nearfull = nearfull + id
if (nvisited (id) == nodes (id).neigh.size)
done = done + id
}
}

val nprocessed = processed ++ todo
if (nearfull.isEmpty)
if (nprocessed.size < visited.size)
visited.keySet -- nprocessed
else
todo
else
getHeight (nodes, nprocessed, nvisited, nearfull)
}
}

 */