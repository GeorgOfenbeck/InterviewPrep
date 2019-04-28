package leetcode.p780

object Solution_Scala {

  def main(args: Array[String]): Unit = {
    /*
    1
8
4
15
     */
    val sx = 1
    val sy = 8
    val tx = 4
    val ty = 15
    //reachingPoints(sx,sy,tx,ty)
    val b = analytic(sx,sy,tx,ty)
    println(b)
    b
  }


  def analytic(sx: Int, sy: Int, tx: Int, ty: Int): Boolean = {
    val (smaller, greater ) = if (sx > sy) (sy,sx) else (sx,sy)

    var gtsum = 0
    var B = 0

    var succtx = false
    while (gtsum < tx){
      gtsum = greater * B
      val remain = tx - gtsum
      if (remain % smaller == 0){
        gtsum = tx
        succtx = true
      } else
        B = B + 1
    }
    if (succtx){
      //solved first
      var gtsum = 0
      var B = 0

      var succtx = false
      while (gtsum < ty){
        gtsum = greater * B
        val remain = ty - gtsum
        if (remain % smaller == 0){
          gtsum = ty
          succtx = true
        } else
          B = B + 1
      }
      succtx
    } else succtx
  }


  def reachingPoints(sx: Int, sy: Int, tx: Int, ty: Int): Boolean = {
    recurse(sx,sy,tx,ty,Map.empty[(Int, Int), Boolean])(tx,ty)
  }


  def recurse(sx: Int, sy: Int, tx: Int, ty: Int, visited: Map[(Int, Int), Boolean]): Map[(Int, Int), Boolean] = {
    if (visited.contains(tx, ty)) visited
    else {
      if (tx < sx || ty < ty) visited + ((tx, ty) -> false)
      else {
        val recursex = recurse(sx, sy, tx - ty, ty, visited)
        if (recursex(tx - ty, ty))
          visited + ((tx, ty) -> true)
        else {
          val recursey = recurse(sx, sy, tx, ty - tx, recursex)
          if (recursey(tx, ty - tx))
            visited + ((tx, ty) -> true)
          else
            visited + ((tx, ty) -> false)
        }
      }
    }
  }



}
