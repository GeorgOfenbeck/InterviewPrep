package leetcode.p119

object Solution {
  def getRow(rowIndex: Int): List[Int] = {
    if (rowIndex == 0) return List(1)
    var res = List(1, 1)
    for (i <- 1 until rowIndex) {
      val next = nextRow(res)
      res = next
    }
    res.reverse
  }

  def nextRow(prevRow: List[Int]): List[Int] = {
    val middle = prevRow.sliding(2).map { _.sum }.toList
    return (1 +: middle :+ 1)
  }

  @main
  def test(): Unit = {
    println(getRow(3))
  }
}
