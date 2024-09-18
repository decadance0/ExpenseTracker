package schemes

trait Scheme[T] {
  def fromSeq(values: Seq[String]): T
}
