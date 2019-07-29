package leetcode.p987

   class TreeNode(var _value: Int) {
    var value: Int = _value
   var left: TreeNode = null
    var right: TreeNode = null
   }


object Solution {

  def main(args: Array[String]):Unit = {
    val _3 = new TreeNode(3)
    val _9 = new TreeNode(9)
    val _20 = new TreeNode(20)
    val _15 = new TreeNode(15)
    val _7 = new TreeNode(7)

    _3.right = _20
    _3.left = _9

    _20.right = _7
    _20.left = _15

    val t = verticalTraversal(_3)
    println(t)

  }
  def verticalTraversal(root: TreeNode): List[List[Int]] = {
    val tmap = traverse(0,0,root,Map.empty)
    tmap.keySet.toVector.sorted.foldLeft(List.empty[List[Int]])( (acc,ele) => {
      val ymap = tmap(ele)
      acc :+ ymap.keySet.toVector.sorted.reverse.foldLeft(List.empty[Int])( (a,e) => {   a ++ ymap(e).sorted })
    })
  }

  def traverse(x: Int, y: Int, tree: TreeNode, tmap: Map[Int,Map[Int, Vector[Int]]]): Map[Int,Map[Int, Vector[Int]]]  = {
    if (tree == null) {
      tmap
    } else {
      val left = traverse(x - 1 , y - 1, tree.left,tmap);
      val ymap = left.getOrElse(x,Map.empty[Int,Vector[Int]])
      val nvec = ymap.getOrElse(y,Vector.empty[Int]) :+ tree.value
      val nymap = (ymap + (y -> nvec))
      val nmap = left + (x -> nymap)
      traverse(x + 1, y -1,tree.right,nmap)
    }
  }
}