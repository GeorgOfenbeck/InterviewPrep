package leetcode.mock30.task1
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Solution {
  def daysBetweenDates(date1: String, date2: String): Int = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTime1 = LocalDate.parse(date1, formatter)
    val dateTime2 = LocalDate.parse(date2, formatter)

    java.time.temporal.ChronoUnit.DAYS.between(dateTime1, dateTime2).abs.toInt
  }

  @main
  def test(): Unit = {
    println(daysBetweenDates("2019-06-29", "2019-06-30")) // 1
    println(daysBetweenDates("2020-01-15", "2019-12-31")) // 15
  }
}
