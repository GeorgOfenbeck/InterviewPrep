package leetcode.p149


case class Pair(x: Int, y: Int)

case class PairChain(maybepos: Option[Pair], maybeneg: Option[Pair])


object Solution_Scala {
  
  def main(args: Array[String]): Unit = {
    val test = Array(Array(1,1),Array(3,2),Array(5,3),Array(4,1),Array(2,3),Array(1,4))
    val test2 = Array(Array(0,0),Array(-1,-1),Array(2,2))
    
    maxPoints3(test2)
  }


  def maxPoints(points: Array[Array[Int]]): Int = {
    ???
  }

  case class LineSet(vertical: Map[Int,Int], rise: Map[Int,Int], horizontal: Map[Int,Int], fall: Map[Int,Int], max: Int)
  def maxPoints3(points: Array[Array[Int]]): Int = {
    if (points == null || points.isEmpty || points(0) == null || points(0).isEmpty) 0
    else {
      val mapped = points.map(p => Pair(p(0), p(1))).toSet
      val fullset = points.foldLeft(LineSet(Map.empty,Map.empty,Map.empty,Map.empty, 0)){
        case (acc,ele) => {
          val p = Pair(ele(0), ele(1))
          val (nvertical,vmax) = nNmapwMax(p.x,acc.vertical)//acc.vertical + ((p.x) -> (acc.vertical.get(p.x).getOrElse(0) + 1))
          val (nrise,rmax) = nNmapwMax(p.y - p.x, acc.rise)//acc.rise + ((p.y - p.x) -> (acc.rise.get(p.y-p.x).getOrElse(0) + 1))
          val (nhorizontal,hmax) = nNmapwMax(p.y + p.x, acc.horizontal)
          //acc.horizontal + ((p.y+p.x) -> (acc.horizontal.get(p.y).getOrElse(0) + 1))
          val (nfall,fmax) = nNmapwMax(p.y + p.x,acc.fall)//acc.fall + ((p.y+p.x) -> (acc.fall.get((p.y+p.x)).getOrElse(0) + 1))
          val nmax = Math.max(vmax,Math.max(rmax,Math.max(hmax,fmax)))
          LineSet(nvertical,nrise,nhorizontal,nfall,nmax)
        }
      }
      fullset.max
    }
  }

  def nNmapwMax(ident: Int, map: Map[Int,Int]): (Map[Int,Int],Int) = {
    val nmap = map + ((ident) -> (map.get(ident).getOrElse(0) + 1))
    (nmap,nmap(ident))
  }

  
  def maxPoints2(points: Array[Array[Int]]): Int = {
    if (points == null || points.isEmpty || points(0) == null || points(0).isEmpty) 0
    else {
      val mapped = points.map(p => Pair(p(0), p(1))).toSet
      val multi = points.foldLeft(Map.empty[Pair,Int]){case (acc,ele) => {
        val p = Pair(ele(0), ele(1))
        if (acc.contains(p))
          acc + (p -> (acc(p) +1 ))
        else
          acc + (p -> 1)
      }}
      Vector(Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(-1,1)).foldLeft(0) { case (fullmax, direction) =>
        val chain = mapped.map(p => p -> new PairChain(help(p, direction.x, direction.y, mapped), help(p, -direction.x, -direction.y, mapped))).toMap
        val ends = chain.filter(p => p._2.maybeneg.isEmpty && p._2.maybepos.isDefined || multi(p._1) > 1)
        val max = ends.keySet.foldLeft(0)((acc, ele) => {
          val followmax = follow(ele, direction, mapped, multi, 0)
          Math.max(acc, followmax)
        })
        Math.max(fullmax, max)
      }
    }
  }

  def follow(x: Pair, direction: Pair, set: Set[Pair], multi: Map[Pair,Int], sofar: Int): Int = {
    val npair = new Pair(x.x + direction.x, x.y + direction.y)
    if (set.contains(npair)) follow(npair, direction, set, multi, sofar + multi(x)) else sofar + multi(x)
  }

  def help(p: Pair, x: Int, y: Int, set: Set[Pair]): Option[Pair] = {
    val np = Pair(p.x + x, p.y + y)
    if (set.contains(np)) Some(np) else None
  }


}
