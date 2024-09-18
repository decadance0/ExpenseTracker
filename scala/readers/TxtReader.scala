package readers

import scala.io.Source
import utils.BasePath

final class TxtReader(override val path: BasePath) extends FileReader {
  override def read(filePath: String): Seq[String] = {

    require(
      filePath.nonEmpty,
      "filePath must be not empty"
    )

    val fullPath = path.concat(filePath)

    try {
      val source = Source.fromFile(fullPath)
      try {
        source.getLines().toSeq
      } finally {
        source.close()
      }
    } catch {
      case e: Exception =>
        throw new RuntimeException(s"Error reading file: $filePath", e)
    }
  }.ensuring(_.nonEmpty, s"File $filePath is empty")
}
