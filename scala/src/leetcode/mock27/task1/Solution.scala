package leetcode.mock27.task1

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import java.time.DayOfWeek

object Solution {
  def dayOfTheWeek(day: Int, month: Int, year: Int): String = {
    val date = LocalDate.of(year, month, day)
    val dayOfWeek: DayOfWeek = date.getDayOfWeek()
    dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
  }
}
