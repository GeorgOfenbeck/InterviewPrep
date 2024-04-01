package leetcode.p015

object Solution {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val onlyDups = toArrayWithOnlyTriples(nums)
    val sorted = onlyDups.sorted // O(n log n)

    var result = Set.empty[List[Int]]
    for (i <- 0 until sorted.length) {
      result = twoSum(sorted, i, result )
    }
    result.toList
  }

  def twoSum(sortedArray: Array[Int], targetpos: Int, prevresult: Set[List[Int]]): Set[List[Int]] = {
    val target = -sortedArray(targetpos)
    var i = 0
    var j = sortedArray.length - 1
    var result = prevresult
    while (i < j) {
      if (i == targetpos) {
        i += 1
      } else if (j == targetpos) {
        j -= 1
      } else {

        val sum = sortedArray(i) + sortedArray(j)
        if (sum == target) {
          val triplet =List(-target, sortedArray(i), sortedArray(j))
          result = result + triplet.sorted 
          i += 1
          j -= 1
        } else if (sum < target) {
          i += 1
        } else {
          j -= 1
        }
      }
    }
    result
  }

  def toArrayWithOnlyTriples(nums: Array[Int]): Array[Int] = {
    val hash = toHash(nums)

    hash
      .foldLeft(List.empty[Int]) { (acc, kv) =>
        if (kv._2 >= 3) {
          kv._1 :: kv._1 :: kv._1 :: acc
        } else if (kv._2 == 2) {

           kv._1 :: kv._1 :: acc
        }   
             else {
          kv._1 :: acc
        }
      }
      .toArray
  }

  def toHash(nums: Array[Int]): Map[Int, Int] = {
    nums.foldLeft(Map.empty[Int, Int]) { (acc, n) =>
      acc + (n -> (acc.getOrElse(n, 0) + 1))
    }
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(-1, 0, 1, 2, -1, -4)
    println(threeSum(nums))
    println(threeSum(Array(0,0,0,0)))
  }
}
