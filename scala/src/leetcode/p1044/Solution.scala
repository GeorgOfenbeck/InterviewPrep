package leetcode.p1044

object Blub {
  @main
  def main() = {
    val res = Solution.longestDupSubstring("aa")
    print(res)
  }
}

object Solution {

  def longestDupSubstring(s: String): String = {
    PostfixTree.cur_longest = 0
    PostfixTree.cur_substring = ""
    val tree = PostfixTree(0)
    for (i <- 0 until s.length()) {
      val sub = s.substring(s.size - i - 1, s.size)
      // println(sub)
      tree.addString(sub, sub)
    }
    //println(PostfixTree.cur_longest)
    //println(PostfixTree.cur_substring)
    //tree.print()
    PostfixTree.cur_substring.substring(0, PostfixTree.cur_longest)
  }

  object PostfixTree {

    var cur_longest = 0
    var cur_substring = ""

  }

  class PostfixTree(length: Int) {
    import PostfixTree._
    val children = scala.collection.mutable.HashMap.empty[Char, PostfixTree]
    var paths = 0
    def addString(postfix: String, original: String): Unit = {
      if (paths >= 2) {
        if (length >= cur_longest) {
          cur_substring = original
          cur_longest = length
        }
      }
      if (postfix.isEmpty()) {
        return
      } else {
        val firstChar = postfix.head
        children.updateWith(firstChar)(subtreeOption =>
          subtreeOption match {
            case None => {
              val subtree = new PostfixTree(length + 1)
              subtree.paths = 1
              subtree.addString(postfix.tail, original)
              Some(subtree)
            }
            case Some(value) => {
              value.paths = value.paths + 1
              value.addString(postfix.tail, original)
              Some(value)
            }
          }
        )
      }
    }

    def print(): Unit = {
      for (k <- children.keySet) {
        for (i <- 0 until length) {
          System.out.print(" ")
        }
        println(s"$k ${children(k).paths}")
        children(k).print()

      }
    }
  }
}
