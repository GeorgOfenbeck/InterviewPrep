package scala.leetcode


object Solution extends App {

  val matrix: Array[Array[Int]] = Array(Array(1,2,3,4),Array(5,6,7,8),Array(9,10,11,12))

  println(spiralOrder(matrix))

  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {

    def add2List(matrix: Array[Array[Int]], direction: Int, sofar: List[Int], curx: Int, cury: Int, xmin: Int, ymin: Int, xmax: Int, ymax: Int): List[Int] = {
     // println(s" $direction $xmin => $xmax $ymin => $ymax ")
      if (xmin > xmax || ymin > ymax) sofar
      else
        direction match {
          case 0 => {
            val app = for (i <- xmin to xmax) yield matrix(cury)(i)
            add2List(matrix = matrix, direction = 1, sofar = sofar ++ app,
              curx = xmax, cury = cury, xmin = xmin, ymin = ymin + 1, xmax = xmax, ymax = ymax)
          }
          case 1 => {
            val app = for (i <- ymin to ymax) yield matrix(i)(curx)
            add2List(matrix = matrix, direction = 2, sofar = sofar ++ app,
              curx = curx, cury = ymax, xmin = xmin, ymin = ymin, xmax = xmax - 1, ymax = ymax)
          }
          case 2 => {
            val app = for (i <- xmax to xmin by -1) yield matrix(cury)(i)
            add2List(matrix = matrix, direction = 3, sofar = sofar ++ app,
              curx = xmin, cury = cury, xmin = xmin, ymin = ymin, xmax = xmax, ymax = ymax - 1)
          }
          case 3 => {
            val app = for (i <- ymax to ymin by -1) yield matrix(i)(curx)
            add2List(matrix = matrix, direction = 0, sofar = sofar ++ app,
              curx = curx, cury = ymin, xmin = xmin + 1, ymin = ymin, xmax = xmax, ymax = ymax)
          }


        }
      }
      add2List(matrix, 0, List.empty, 0, 0, 0, 0, matrix(0).size - 1, matrix.size - 1)



  }
}