package leetcode.p348

class TicTacToe(_n: Int) {
  val rows: scala.collection.mutable.Map[Int, Int] = scala.collection.mutable.Map.empty
  val cols: scala.collection.mutable.Map[Int, Int] = scala.collection.mutable.Map.empty
  var diag: Int = 0
  var diag2: Int = 0

  def move(row: Int, col: Int, player: Int): Int = {
    val sign = if(player == 2) -1 else 1
    if (row == col) {
      diag = diag + 1 * sign
    }
    if(_n - 1 - row == col){
      diag2 = diag2 + 1 * sign
    }
    rows.updateWith(row) { option =>
      {
        val x = option match{
          case None        => 1 * sign
          case Some(value) => value + 1 * sign
        }
        Some(x)
      }
    }
    cols.updateWith(col) { option =>
      {
        val x = option match{
          case None        => 1 * sign
          case Some(value) => value + 1 * sign
        }
        Some(x)
      }
    }
    if(Math.abs(diag) == _n || Math.abs(cols(col)) == _n || Math.abs(rows(row)) == _n || Math.abs(diag2) == _n)
    {
        return player 
    } else {
        return 0
    }
  }

}

object blub {
    @main
    def main() = {

    }
}

/** Your TicTacToe object will be instantiated and called as such: var obj = new
  * TicTacToe(n) var param_1 = obj.move(row,col,player)
  */
