package leetcode.p336

import scala.util.boundary


object Solution {
    val print = false


    def palindromePairs(words: Array[String]): List[List[Int]] = {
        val forpairs = palindromePairsForward(words)
        val backpairs = palindromePairsForward(words.reverse).map(v => List(words.length - v(0) - 1, words.length - v(1) - 1))
        forpairs ++ backpairs
    }
    def palindromePairsForward(words: Array[String]): List[List[Int]] = {
        val (forwardtree, forwardlist) = words.zipWithIndex.foldLeft((PrefixTree(), List.empty[List[Int]]))(
            (acc , pair) =>  {
                val (word, idx) = pair
                val (prefixTree, result) = acc
                val reversed = word.reverse
                val searchResult = prefixTree.search(word)
                val newtree = prefixTree.insert(reversed, idx) 
                (newtree, result ++ searchResult.toList.map(i => List( idx, i)))
            }
        )
        if (print){
        forwardtree.printTree()
        println(forwardlist)
        println("==============")
    }
        forwardlist
    }
        
    


    def main(args: Array[String]): Unit = {
        /*{
        val words = Array("abcd", "dcba", "lls", "s", "sssll")
        val result = palindromePairs(words)
        result.foreach(v => println(v.mkString("[", ", ", "]")))
        }*/
        {
        println("==============")
        val words = Array("a", ""  )
        val result = palindromePairs(words)
        result.foreach(v => println(v.mkString("[", ", ", "]")))
        }
    }
}

object PrefixTree {
    def apply(): PrefixTree = PrefixTree(Map.empty, Set.empty, Set.empty)    
}

case class PrefixTree(childen: Map[Char, PrefixTree], terminal: Set[Int], remainPalindrome: Set[Int]) 
{
    def insert(word: String, idx: Int ): PrefixTree = {
        if (word.isEmpty) {
            PrefixTree(childen, terminal + idx, remainPalindrome)
        } else {
            val head = word.head
            val tail = word.tail
            val child = childen.getOrElse(head, PrefixTree())
            val newChild = child.insert(tail, idx)
            val restPalindrome = if (isPalindrome(tail)) remainPalindrome + idx else remainPalindrome
            PrefixTree(childen + (head -> newChild), terminal, restPalindrome)
        }
    }

    def search(word: String): Set[Int] = {
        if (word.isEmpty) {
            terminal ++ remainPalindrome
        } else {
            val head = word.head
            val tail = word.tail
            childen.get(head) match {
                case Some(child) => child.search(tail)
                case None => Set.empty
            }
        }
    }

    def isPalindrome(word: String): Boolean = {
        import scala.util.boundary
        import boundary.break
        boundary[Boolean] {
            val n = word.length
            for (i <- 0 until n / 2) {
                if (word(i) != word(n - i - 1)) {
                    break(false)
                }
            }
            true
        }
    }
    
    def printTree(indent: Int = 0): Unit = {
        //println("PrefixTree")
        println(s"terminal: $terminal")
        println(s"remainPalindrome: $remainPalindrome")
        childen.foreach { case (k, v) => 
            for (i <- 0 until indent) {
                print(" ")
            }
            println(s"key: $k")
            v.printTree(indent + 3)
        }
    }
} 
