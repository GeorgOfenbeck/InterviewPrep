package leetcode.p118

object Solution {
  def generate(numRows: Int): List[List[Int]] = {
    if (numRows == 0) return List()
    if (numRows == 1) return List(List(1))

    var res = List(List(1,1),List(1))
    for (i <- 2 until numRows) {
      res = nextRow(res.head) +: res
    }
    res.reverse
  }

  def nextRow(prevRow: List[Int]): List[Int] = {
    val middle = prevRow.sliding(2).map { _.sum }.toList
    return (1 +: middle :+ 1)
  }

  @main
  def test(): Unit = {
    println(generate(5))
  }
}
