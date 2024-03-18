package leetcode.p221

object Solution_Scala {
  def maximalSquare(matrix: Array[Array[Char]]): Int = {
    if (matrix == null || matrix.length == 0) 0 else {
      val buffer = new Array[Array[Int]](matrix.length + 1)
      for (i <- 0 until matrix.length + 1) buffer(i) = new Array[Int](matrix(0).length + 1)

      var res = 0
      for (i <- 0 until matrix.length)
        for (j <- 0 until matrix(i).length){
          res = Math.max(getGreatest(matrix,buffer,i,j),res)
        }
      res
    }
  }

  def getGreatest(matrix: Array[Array[Char]], buffer: Array[Array[Int]], x: Int, y: Int): Int = {
    if (matrix(x)(y) == '0') {
      buffer(x+1)(y+1) = 0
      0
    }
    else {
      val left = buffer(x+1)(y+1-1)
      val up = buffer(x+1-1)(y+1)
      val diag = buffer(x)(y)
      val res = Math.min(Math.min(left,up),diag)
      buffer(x+1)(y+1) = res +1
      res +1
    }
  }

}
