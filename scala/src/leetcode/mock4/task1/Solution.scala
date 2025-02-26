package leetcode.mock4.task1
// To execute Scala code, please define an object named Solution that extends App

object Solution {

  case class Logs(
      id: String,
      log: String
  )

  def reorderLogFiles(logs: Array[String]): Array[String] = {
    val asLog: Array[Logs] = logs.map(log => getId(log))
    val (letterlog, digitlog) = asLog.partition(l => !isDigit(l.log))
    val letterSorting: Ordering[Logs] = Ordering.by(x => (x.log, x.id))
    val sortedLetters = letterlog.sorted(letterSorting)

    sortedLetters.map(x => s"${x.id} ${x.log}") ++ digitlog.map(x =>
      s"${x.id} ${x.log}"
    )
  }

  def isDigit(a: String): Boolean = {
    a(0).isDigit
  }

  def getId(a: String): Logs = {
    val firstSpace = a.indexOf(" ")
    val (id, rest) = a.splitAt(firstSpace)
    Logs(id, rest)
  }
}
