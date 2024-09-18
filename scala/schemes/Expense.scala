package schemes

import java.time.LocalDate
import transformers.DateTimeParser


case class Expense(
                    id: Int,
                    date: LocalDate,
                    product: String,
                    category: String,
                    cost: Float,
                    currency: String
                  )

object Expense extends Scheme[Expense] {

  private val datePattern = "yyyy-MM-dd"

  def fromSeq(values: Seq[String]): Expense = {
    require(
      values.nonEmpty,
      "Sequence is empty"
    )

    try {
      Expense(
        id = values(0).toInt,
        date = DateTimeParser.parseDate(values(1), datePattern),
        product = values(2),
        category = values(3),
        cost = values(4).toFloat,
        currency = values(5)
      )
    } catch {
      case e: Exception =>
        throw new RuntimeException(s"Unexpected error occurred during parsing Seq: ${e.getMessage}", e)
    }
  }
}