object Solution {
  def setZeroes(matrix: Array[Array[Int]]): Unit = {

    var firstRow = false
    var firstColumn = false

    for (i <- 0 until matrix.size) {
      for (j <- 0 until matrix(i).size) {
        if (matrix(i)(j) == 0) {
          if (i == 0 || j == 0) {
            if (i == 0)
              firstRow = true
            if (j == 0)
              firstColumn = true
          } else {
            matrix(0)(j) = 0
            matrix(i)(0) = 0
          }
        }
      }
    }

    for (i <- 1 until matrix.size) {
      for (j <- 1 until matrix(i).size) {
        if (matrix(0)(j) == 0 || matrix(i)(0) == 0)
          matrix(i)(j) = 0
      }
    }

    if (firstRow)
      for (j <- 0 until matrix(0).size)
        matrix(0)(j) = 0

    if (firstColumn)
      for (i <- 0 until matrix.size)
        matrix(i)(0) = 0

  }

}
