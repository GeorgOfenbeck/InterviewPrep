package leetcode.p472

import scala.collection.{mutable => mu}

case class Trie(letter: Char, children: Map[Char, Trie], isTerminal: Boolean)

object Solution {
  def findAllConcatenatedWordsInADict(words: Array[String]): List[String] = {
    val trie = stringsToTrie(words)
    val cache = mu.Map.empty[String, Boolean]

    val res = words.foldLeft(List.empty[String]) { (acc, ele) =>
      {
        if (isComposable(cache, ele, trie, trie, Vector.empty, 0)) {
          ele +: acc
        } else {
          acc
        }
      }
    }
    res.toSet.toList
  }

  def isInTrie(word: String, trie: Trie): Boolean = {
    if (word.isEmpty) {
      return false
    }

    val head = word.head
    trie.children.get(head) match {
      case Some(child) =>
        if (word.tail.isEmpty) {
          return child.isTerminal
        }
        isInTrie(word.tail, child)
      case None =>
        false
    }
  }

  def stringsToTrie(words: Array[String]): Trie = {

    val init = Trie('*', Map.empty, false)
    words.foldLeft(init) { (trie, word) =>
      createTrie(word.toList, trie)
    }
  }

  def createTrie(word: List[Char], trie: Trie): Trie = {
    if (word.isEmpty) {
      trie.copy(isTerminal = true)
    } else {
      val head = word.head
      val tail = word.tail

      trie.children.get(head) match {
        case Some(child) =>
          trie.copy(children =
            trie.children + (head -> createTrie(tail, child))
          )
        case None =>
          val newChild = Trie(head, Map.empty, false)
          trie.copy(children =
            trie.children + (head -> createTrie(tail, newChild))
          )
      }
    }
  }

  def isComposable(
      cache: mu.Map[String, Boolean],
      word: String,
      trie: Trie,
      trieRoot: Trie,
      prefix: Vector[Char],
      nrSplits: Int
  ): Boolean = {

    // take letter by letter from word - move in trie

    // whenever we hit teminal node, we can start from the beginning (trieRoot -> rest of word)
    // always check cache if we have already seen the rest word
    if (word.isEmpty) {
      return false
    }

    val head = word.head
    trie.children.get(head) match {
      case Some(child) =>
        // we have hit a prefix -> recurse for the postfix
        if (child.isTerminal) {
          // postfix
          val rest = word.tail

          if (rest.nonEmpty) {

            if (isInTrie(rest, trieRoot)) {
              return true
            }
            if (cache.contains(rest)) {
              return cache(rest)
            } else {
              if (
                isComposable(
                  cache,
                  rest,
                  trieRoot,
                  trieRoot,
                  Vector.empty,
                  nrSplits + 1
                )
              ) {
                val prefixString = prefix.mkString + head
                cache.addOne(prefixString + rest -> true)
                return true
              } else {
                cache.addOne(prefix.mkString + head + rest -> false)
                // we continue with the rest of the word
              }
            }
          } else {
            return nrSplits > 0
          }

        }
        isComposable(
          cache,
          word.tail,
          child,
          trieRoot,
          prefix :+ head,
          nrSplits
        )
      case None =>
        false
    }
  }

  @main
  def test(): Unit = {
    val words = Array(
      "cat",
      "cats",
      "catsdogcats",
      "dog"
    )
    val res = findAllConcatenatedWordsInADict(words)
    println(res)
  }

  @main
  def test5(): Unit = {

    val words = Array(
      "ores",
      "es",
      "or"
    )
    val trie = stringsToTrie(words)
    println(isInTrie("ores", trie))
    println(isInTrie("or", trie))
    println(isInTrie("es", trie))
    println(isInTrie("esx", trie))

    val res = findAllConcatenatedWordsInADict(words)
    println(res)
  }

  @main
  def test6(): Unit = {

    val words = Array(
      "cat",
      "cats",
      "catsdogcats",
      "dog",
      "dogcatsdog",
      "hippopotamuses",
      "rat",
      "ratcatdogcat"
    )

    val res = findAllConcatenatedWordsInADict(words)
    println(res)
  }

  @main
  def test3(): Unit = {

    val words = Array(
      "a",
      "aa",
      "aaa",
      "aaaa",
      "aaaaa",
      "aaaaay"
    )

    val res = findAllConcatenatedWordsInADict(words)
    println(res)
  }
  @main
  def test4(): Unit = {
    import scala.io.Source
    val input = {
      val fileContent =
        Source.fromFile("./scala/src/leetcode/p472/input.txt").mkString
      fileContent.trim.stripPrefix("\"").stripSuffix("\"").split("\",\"")
    }

    val output = {
      val fileContent =
        Source.fromFile("./scala/src/leetcode/p472/output.txt").mkString
      fileContent.trim.stripPrefix("\"").stripSuffix("\"").split("\",\"")
    }

    val res = findAllConcatenatedWordsInADict(input)

    // println(res.sorted())
    // println("######################")
    // println(output.toList.sorted())
    val same = output.toSet.intersect(res.toSet)
    println(same.toList.sorted())
    println("DIFF only out: ++++++++++++++++++++++")
    val diff = output.toSet -- res.toSet
    println(diff.toList.sorted())

    val trie = stringsToTrie(input)
    println(isInTrie("show", trie))
    println(isInTrie("times", trie))
    println(isInTrie("showtimes", trie))

    println(
      isComposable(
        mu.Map.empty,
        "show",
        trie,
        trie,
        Vector.empty,
        0
      )
    )
  }
}
