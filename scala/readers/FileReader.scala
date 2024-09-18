package readers

import utils.BasePath

trait FileReader {
  val path: BasePath
  def read(filePath: String): Seq[String]
}
