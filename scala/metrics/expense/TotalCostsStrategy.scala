package metrics.expense

import metrics.MetricStrategy
import schemes.Expense

class TotalCostsStrategy extends MetricStrategy[Expense] {
  protected val resultMessage: String = "Total costs"

  override def calculate(values: Seq[Expense]): String = {
    val totalCost = values.map(_.cost).sum
    totalCost.toString
  }
}

