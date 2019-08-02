package hackerrank.MagicSquareForming


object Solution_Scala {

  // Complete the formingMagicSquare function below.
  def formingMagicSquare(s: Array[Array[Int]]): Int = {

    val copy = Vector(
      s(0)(0), s(0)(1), s(0)(2),
      s(1)(0), s(1)(1), s(1)(2),
      s(2)(0), s(2)(1), s(2)(2),
    )
    ???
  }

  case class MagicV(v: Vector[Int]) {
    def _abc = v(0) + v(1) + v(2)

    def _def = v(3) + v(4) + v(5)

    def _ghi = v(6) + v(7) + v(8)

    def _adg = v(0) + v(3) + v(6)

    def _beh = v(1) + v(4) + v(7)

    def _cfi = v(2) + v(5) + v(8)

    def _aei = v(0) + v(4) + v(8)

    def _gec = v(6) + v(4) + v(2)

    def isMagic: Boolean = _abc == _def && _abc == _ghi && _abc == _adg &&
      _abc == _beh && _abc == _cfi && _abc == _aei && _abc == _gec



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
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val s = Array.ofDim[Int](3, 3)

    for (i <- 0 until 3) {
      s(i) = stdin.readLine.split(" ").map(_.trim.toInt)
    }

    val result = formingMagicSquare(s)

    printWriter.println(result)

    printWriter.close()
  }
}
