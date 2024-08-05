package leetcode.p038

object Solution {
  @main
  def main()={
    val x:BigInt = 3322251
    val rle = Rle.fromBigInt(x)
    println(s"$rle ${rle.toBigInt()} ${countAndSay(2)}")
  }

  def countAndSay(n: Int): String = {
    var cur: BigInt = 0
    for (i <- 1 to n){
       if  (i == 1)
        cur = 1
       else{
        cur = Rle.fromBigInt(cur).toBigInt()             
       }
    }
    cur.toString()       
  }
  

  object Rle {
    def fromBigInt(l: BigInt): Rle = {
      var remain: BigInt = l
      var numbers = List.empty[List[Int]]
      var count: Int = 1

      var prevdigit: Int = (remain % 10).toInt
      remain = remain / 10

      while ({
        val digit: Int = (remain % 10).toInt
        remain = remain / 10
        if (digit == prevdigit) {
          count = count + 1
        } else {
          numbers = List(count, prevdigit) +: numbers
          prevdigit = digit
          count = 1
        }
        remain > 0
      }) ()
      if (prevdigit != 0)
      numbers = List(count, prevdigit) +: numbers

      Rle(numbers)

    }
  }

  class Rle(val numbers: List[List[Int]]) // [#digits, digit]
  {
    override def toString(): String = {
        val sb: StringBuffer = StringBuffer()
        
        for (n <- numbers){
            sb.append(n(0).toString())
            sb.append(n(1).toString())
        }
        sb.toString()
    }
    def toBigInt(): BigInt = {
        var x: BigInt = 0
        for (n <- numbers){
            /*for (r <- 0 until n(0))
            {
                x = x * 10
                x = x + n(1) 
            }*/
            x = x * 10
            x = x + n(0)
            x = x * 10
            x = x + n(1)
        }
        x
    }
  }
}
