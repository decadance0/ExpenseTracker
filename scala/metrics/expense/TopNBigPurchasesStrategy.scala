package metrics.expense

import metrics.MetricStrategy
import schemes.Expense

class TopNBigPurchasesStrategy(n: Int) extends MetricStrategy[Expense] {
  protected val resultMessage: String = s"Top $n costs"

  override def calculate(values: Seq[Expense]): String = {
    val topNValues = values.sortBy(-_.cost).take(n)

    val formattedValues = topNValues.map(expense => s"${expense.product} ${expense.cost} ${expense.currency}")

    formattedValues.mkString(", ")
  }
}
