package leetcode.p079

object Solution {

  case class Pos(val x: Int, val y: Int, val c: Char)
  def exist(board: Array[Array[Char]], word: String): Boolean = {

    val mutmap = scala.collection.mutable.Map.empty[Char, Pos]
    var seeds = List.empty[Pos]
    var posset = Set.empty[Pos]
    for (x <- 0 until board.length) {
      for (y <- 0 until board(x).length) {
        val pos =Pos(x, y, board(x)(y))
        mutmap.put(board(x)(y),pos)
        posset = posset + pos
        if (board(x)(y)==word(0))
            seeds = pos +: seeds
      }
    }

    val wordList = word.toList
    
    
    // make sure that its even possible
    var check = wordList
    while(!check.isEmpty){  
      if (!mutmap.contains(check.head)) {
        return false
      }
      check = check.tail
    }

    
    var res = false
    while(!seeds.isEmpty && !res){
        res = rec(seeds.head, wordList.tail, posset, board)
        seeds = seeds.tail
    }
    res

  }

  def rec(
      curPos: Pos, //char that we are checking
      wordList: List[Char], //char to check next
      posSet: Set[Pos], //
      board: Array[Array[Char]]
  ): Boolean = {
    

    if (!posSet.contains(curPos)) return false
    else {
      
      //println(s"matched ${curPos.c}") 
      if (wordList.isEmpty) return true
      
      val focusChar = wordList.head
      val up = Pos(curPos.x + 1, curPos.y, focusChar)
      val down = Pos(curPos.x - 1, curPos.y, focusChar)
      val left = Pos(curPos.x, curPos.y - 1, focusChar)
      val right = Pos(curPos.x, curPos.y + 1, focusChar)

      rec(up, wordList.tail, posSet - curPos, board) ||
      rec(down, wordList.tail, posSet - curPos, board) ||
      rec(left, wordList.tail, posSet - curPos, board) ||
      rec(right, wordList.tail, posSet - curPos, board) 
    }

  }

  @main
  def main() = {
    val grid =Array(Array('A','B','C','E'),Array('S','F','C','S'),Array('A','D','E','E'))
    exist(grid, "ABCCED")
  }
}
