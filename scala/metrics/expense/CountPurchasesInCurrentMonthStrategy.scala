package metrics.expense

import java.time.{LocalDate, Month, Year}
import metrics.MetricStrategy
import schemes.Expense

class CountPurchasesInCurrentMonthStrategy extends MetricStrategy[Expense] {
  protected val resultMessage: String = "Total purchases in current month"

  protected def compareDate(date: LocalDate): Boolean = {
    date.getMonth == Month.JULY && date.getYear == 2023
  }

  override def calculate(values: Seq[Expense]): String = {
    val purchases = values.filter(expense => compareDate(expense.date))
    purchases.size.toString
  }
}
