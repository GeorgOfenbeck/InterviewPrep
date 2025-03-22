package leetcode.mock31.task2

object Solution {
  def diagonalSort(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val target = Array.ofDim[Int](mat.size, mat(0).size)
    for (i <- 0 until mat(0).size) {
      sortDiag(0, i, mat, target)
    }
    for (i <- 1 until mat.size) {
      sortDiag(i, 0, mat, target)
    }
    target
  }

  def sortDiag(
      startx: Int,
      starty: Int,
      mat: Array[Array[Int]],
      target: Array[Array[Int]]
  ): Unit = {
    var l = List.empty[Int]

    val right = mat(0).size
    val bottom = mat.size
    var i = 0
    while (startx + i < bottom && starty + i < right) {
      l = mat(startx + i)(starty + i) +: l
      i += 1
    }
    l = l.sorted
    i = 0
    while (startx + i < bottom && starty + i < right) {
      target(startx + i)(starty + i) = l.head
      l = l.tail
      i += 1
    }
  }

  def test(): Unit = {
    val mat = Array(
      Array(3, 3, 1, 1),
      Array(2, 2, 1, 2),
      Array(1, 1, 1, 2)
    )
    val res = diagonalSort(mat)
    println(res.map(_.mkString(" ")).mkString("\n"))
  }
}
