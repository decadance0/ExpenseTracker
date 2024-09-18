package utils

final case class BasePath(basePath: String) {
  def concat(file: String): String = {
    basePath + file
  }
}
