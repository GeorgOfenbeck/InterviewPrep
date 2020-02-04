package leetcode.p123
/*
abstract class POI(val x: Int, val pos: Int)

case class Min(override val x: Int, override val pos: Int) extends POI(x, pos)

case class Max(override val x: Int, override val pos: Int) extends POI(x, pos)

case class Profit(min: Min, max: Max){
  val profit: Int = max.x - min.x
}

object Solution_Scala extends App {
  val test = Array(6,1,3,2,4,7)
  //val test = Array(2,2,5)
  //val test = Array(7,6,4,3,1)
  //val test = Array(0,2,1,3,1,2,0,4,0,3)
  println(maxProfit(test))

  def maxProfit(prices: Array[Int]): Int = {
    if (prices == null) return 0
    if (prices.length == 0) return 0

    if (prices.length == 1) return 0;
    if (prices.length == 2) {
      if (prices(0) < prices(1)) return prices(1) - prices(0) else return 0
    }
    val pois = getMinMax(prices)
    if (pois.length == 0){
      if (prices(0) < prices(prices.length-1))
        return prices(prices.length-1) - prices(0)
      else
        return 0
    }
    /*
    if (pois.length == 2 ){
      pois(0) match{
        case x: Max => return 0
        case x: Min => {
          val t = pois(0)
          val u = pois(1)
          return u.x - t.x
        }
      }
    }*/
    //at this point we have at least 2 Pois - meaning at least one max after a min
    val ws = pois//if (pois.head.isInstanceOf[Max]) pois.tail else pois

    val profit = toProfit(ws)
    maxProfit(1,profit)
  }

  def toProfit(x: List[POI]): List[Profit] = {
    if (x.length %2 == 1) throw new IllegalArgumentException
    x match {
      case (a: Min) :: (b: Max) :: t => Profit(a,b) :: toProfit(t)
      case Nil  => List()
      case _ => List()
    }
  }


  def maxProfit(k: Int, pois: List[Profit]): Int ={
    /*
    pois.foldLeft(pois.head){
      (acc,ele) => {
        if (ele.profit > acc.profit)
          ele
        else {
          val merge = Profit(acc.min,ele.max)
          if (merge.profit > acc.profit) merge else
          acc
        }
      }
    }.profit */
    ???
    
  }
  

  def getMinMax(prices: Array[Int]): List[POI] = {
    var ll: List[POI] = List()
    //min = min :+ prices(0)
    //if (prices(0) < prices(1))
    //  ll = ll :+ new Min(prices(0), 0)
    var isMin = false
    for (i <- 1 until prices.length - 1) {
      if (prices(i - 1) >= prices(i) && prices(i + 1) > prices(i)) {
        ll = ll :+ new Min(prices(i), i)
        isMin = true
      }
      if (prices(i - 1) <= prices(i) && prices(i + 1) < prices(i)) {
        ll = ll :+ new Max(prices(i), i)
        isMin = false
      }
    }
    //if (prices(prices.length - 1) > prices(prices.length - 2))
    //  ll = ll :+ new Max(prices(prices.length - 1),prices.length-1)

    if (ll.isEmpty) return ll
    if (ll.head.isInstanceOf[Max])
      ll = Min(prices(0),0) +: ll
    if (isMin)
      ll = ll :+ Max(prices(prices.length-1), prices.length-1)
    ll
  }

}*/