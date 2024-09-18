package jobs

abstract class Job[T] {

  protected def read(): Seq[String]

  protected def transform(values: Seq[String]): Seq[T]

  protected def write(values: Seq[T]): Unit

  def run(): Unit = {
    val rawValues = read()
    val transformedValues = transform(rawValues)
    write(transformedValues)
  }
}
