package jobs

import configs.Config.basePath

import schemes.Expense
import readers.TxtReader
import transformers.Transformer
import writers.ConsoleWriter

import metrics.MetricProcessor
import metrics.expense.TotalCostsStrategy
import metrics.expense.CountPurchasesInCurrentMonthStrategy
import metrics.expense.TopNBigPurchasesStrategy
import metrics.expense.HighestSpendingCategoryStrategy


class ExpenseTracker(filePath: String) extends Job[Expense] {
  protected override def read(): Seq[String] = {
    val reader = new TxtReader(basePath)

    reader.read(filePath)
  }

  protected override def transform(expenses: Seq[String]): Seq[Expense] = {
    val seqSeparator = ","

    val withSeparatedValues = Transformer.splitStrToSeq(expenses, seqSeparator)

    withSeparatedValues.map(Expense.fromSeq)
  }

  protected override def write(expenses: Seq[Expense]): Unit = {

    val consoleWriter = new ConsoleWriter

    val metricProcessor = new MetricProcessor[Expense](consoleWriter)

    val totalCostsStrategy = new TotalCostsStrategy

    metricProcessor.run(
      strategy = totalCostsStrategy,
      values = expenses
    )

    val countPurchasesStrategy = new CountPurchasesInCurrentMonthStrategy

    metricProcessor.run(
      strategy = countPurchasesStrategy,
      values = expenses
    )

    val top10BigPurchasesStrategy = new TopNBigPurchasesStrategy(10)

    metricProcessor.run(
      strategy = top10BigPurchasesStrategy,
      values = expenses
    )

    val highestSpendingCategoryStrategy = new HighestSpendingCategoryStrategy

    metricProcessor.run(
      strategy = highestSpendingCategoryStrategy,
      values = expenses
    )
  }
}