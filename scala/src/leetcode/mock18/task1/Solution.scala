package leetcode.mock18.task1

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def numUniqueEmails(emails: Array[String]): Int = {

    val simplified = emails.map { mail =>
      val (prefix, domain) = mail.split("\\@") match {
        case Array(p, d) => (p, d)
      }
      val beforePlus =
        if (prefix.contains("+")) prefix.split("\\+").head else prefix
      val withoutDot = beforePlus.filter(c => c != '.')
      withoutDot + "@" + domain
    }

    simplified.toSet.size
  }

}
