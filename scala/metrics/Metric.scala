package metrics

import writers.Writer

abstract class MetricStrategy[T] {
  def showInfoMessage(): Unit = {
    require(
      resultMessage != null && resultMessage.nonEmpty,
      "Result message is not defined"
    )

    println(s"$resultMessage ")
  }

  protected val resultMessage: String

  def calculate(values: Seq[T]): String
}

class MetricProcessor[T](writer: Writer) {
  def run(strategy: MetricStrategy[T], values: Seq[T]): Unit = {
    try {
      require(strategy != null, "Strategy cannot be null")
      require(writer != null, "Writer cannot be null")
      require(values != null, "Values cannot be null")

      val result = strategy.calculate(values)

      strategy.showInfoMessage()

      writer.write(result)
    } catch {
      case e: Exception =>
        println(s"An unexpected error occurred: ${e.getMessage}")
    }
  }
}