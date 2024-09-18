package metrics.expense

import metrics.MetricStrategy
import schemes.Expense

class HighestSpendingCategoryStrategy extends MetricStrategy[Expense] {
  protected val resultMessage: String = "The highest spending category"

  override def calculate(values: Seq[Expense]): String = {
    val categoryTotals = values
      .groupBy(_.category)
      .map { case (category, expenses) =>
        category -> expenses.map(_.cost).sum
      }

    categoryTotals.maxBy(_._2)._1
  }
}
