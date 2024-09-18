package writers

class ConsoleWriter extends Writer {
  override def write(output: String): Unit = {
    println(output)
  }
}
