package transformers

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateTimeParser {
  def parseDate(date: String, pattern: String): LocalDate = {
    val datePattern = DateTimeFormatter.ofPattern(pattern)
    LocalDate.parse(date, datePattern)
  }
}
