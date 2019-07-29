/*
package hackerrank.DivisibleSumPairs

import scala.collection.generic.{CanBuild, CanBuildFrom}
import scala.collection.{GenSeqLike, mutable}


object Solution_Scala {
  import scala.collection.generic.{CanBuild, CanBuildFrom}

  // Complete the divisibleSumPairs function below.
  def divisibleSumPairs(n: Int, k: Int, ar: Array[Int]): Int = {
    val mods = ar.zipWithIndex.map{ case (value,idx) => {
      val mod = value % k
      mod -> idx
    }}

    def addifexits[K,V,S[V] <: GenSeqLike[V]](m: Map[K,S[V]],k: K, v: V)(implicit bf: CanBuildFrom[S[V],V,S[V]]): Map[K,S[V]] = {
      val ov = m.get(k)
      val x = if (ov.isEmpty){
        println(s"empty $k $v")
        bf.apply().+=(v).result()
      }
      else {
        val sofar = ov.get
        println(s"exists $k $v")
        val builder = sofar.foldLeft(bf.apply())(
          (acc,ele) => acc.+=(ele)
        )
        val tt: S[V] = builder.+=(v).result()
        tt
      }
      m.+((k -> x))
      /*
      val x: S[V] = m.get(k).fold({
        println(s"empty $k $v")
        bf.apply().+=(v).result()
      }
        )({
        println(s"exists $k $v")
        sofar => bf.apply(sofar).+=(v).result()
      })
      m.+((k -> x))*/
    }

    val modidx: Map[Int, Vector[Int]] = mods.foldLeft(Map.empty[Int,Vector[Int]]){ (acc,ele) => {
        val (mod,idx) = ele
        addifexits(acc,mod,idx)
      }
    }

    val x = modidx.map( e => {
      val (mod, idx) = e

      val complement = k - mod

      val res: Int = if (mod == 0) ((idx.length * idx.length) - idx.length)/2
      else
        if (!modidx.contains(complement)) 0 else
        if (mod == complement) //e.g (n = 6 mod = 3)
        {
          ((idx.length * idx.length) - idx.length)/2
        } else {
        if (mod < complement) idx.length * modidx(complement).size
        else 0
      }
      res
    })
    x.fold(0)( (a,b) => a + b)

  }

  def main(args: Array[String]) {
    import java.io._
    import java.math._
    import java.security._
    import java.text._
    import java.util._
    import java.util.concurrent._
    import java.util.function._
    import java.util.regex._
    import java.util.stream._

    val n = 6
    val k = 3
    val ar = Array(1 ,3 ,2 ,6 ,1 ,2)
    val result = divisibleSumPairs(n, k, ar)

  }
}
*/