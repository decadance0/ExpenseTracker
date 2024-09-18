package transformers

object Transformer {
  def splitStrToSeq(iter: Seq[String], sep: String): Seq[Seq[String]] = {
    require(sep.nonEmpty, "Separator cannot be an empty string")

    try {
      iter.map { str =>
        require(str.nonEmpty, "String in sequence cannot be empty")
        str.split(sep).toSeq
      }
    } catch {
      case e: Exception =>
        throw new RuntimeException(s"Unexpected error occurred during split: ${e.getMessage}", e)
    }
  }.ensuring(seq => seq.nonEmpty && seq.forall(_.nonEmpty), "Sequence or its elements are empty")
}