package leetcode.p874

object Solution_Scala {


  def main(args: Array[String]):Unit = {

    val moves: Array[Int] = Array(-2,-1,-2,3,7)
    val ob: Array[Array[Int]] = Array(Array(1,-3),Array(2,-3),Array(4,0),Array(-2,5),Array(-5,2),Array(0,0),Array(4,-4),Array(-2,-5),Array(-1,-2),Array(0,2))
    robotSim(moves,ob)
  }

  case class Pair(x: Int, y: Int)

  case class State(max: Int, pos: Pair, direction: Int)

  def robotSim(commands: Array[Int], obstacles: Array[Array[Int]]): Int = {
    val oset = obstacles.map(x => Pair(x(0), x(1))).toSet

    val res = commands.foldLeft(State(0, Pair(0, 0), 0))({
      (acc, ele) => {
        if (ele == -1)
          acc.copy(direction = (acc.direction + 1) % 4)
        else {
          if (ele == -2)
            acc.copy(direction = if (acc.direction == 0) 3 else acc.direction - 1)
          else {
            val vel = acc.direction match {
              case 0 => Pair(0, 1)
              case 1 => Pair(1, 0)
              case 2 => Pair(0, -1)
              case 3 => Pair(-1, 0)
            }
            val moves = (1 to ele).takeWhile(p => !oset.contains(Pair(acc.pos.x + vel.x * p, acc.pos.y + vel.y * p)))
            val newpos = if (moves.isEmpty) acc.pos
            else
             Pair(acc.pos.x + vel.x * moves.last, acc.pos.y + vel.y * moves.last)
            acc.copy(max = Math.max(eug(newpos), acc.max), pos = newpos)
          }
        }
      }
    })
    res.max
  }

  def eug(x: Pair): Int = x.x * x.x + x.y * x.y

}


