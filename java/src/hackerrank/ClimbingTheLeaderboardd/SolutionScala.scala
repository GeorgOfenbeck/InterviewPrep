package hackerrank.ClimbingTheLeaderboardd





object SolutionScala {

  // Complete the climbingLeaderboard function below.
  def climbingLeaderboard(scores: Array[Int], alice: Array[Int]): Array[Int] = {

    val tset = scala.collection.immutable.TreeSet(scores:_*)



    val (vec, rset) = alice.foldLeft( (Vector.empty[Int],tset) )( (acc,ele) => {
      val (arr,set) = acc
      val nset = set + ele
      val hset = nset.from(ele)

      val nsize = nset.size
      val hsize = hset.size
     // println(s"totalsize: ${nsize} - hset size ${hsize}")


      (arr :+ hsize, nset)
    })

   // println(vec)
    vec.toArray
  }

  def main(args: Array[String]) {
    import java.util._
    val stdin = scala.io.StdIn

    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
    /*
        val scoresCount = stdin.readLine.trim.toInt

        val scores = stdin.readLine.split(" ").map(_.trim.toInt)
        val aliceCount = stdin.readLine.trim.toInt

        val alice = stdin.readLine.split(" ").map(_.trim.toInt)
        */
    val scores = Array(100, 90, 90, 80, 75, 60)
    val alice = Array(50, 65, 77, 90, 102)
    val result = climbingLeaderboard(scores, alice)


    //printWriter.println(result.mkString("\n"))

    //printWriter.close()
  }
}
