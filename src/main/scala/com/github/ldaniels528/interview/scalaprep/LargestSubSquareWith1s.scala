package com.github.ldaniels528.interview.scalaprep

/**
  * Given an NxM matrix, find the largest sub-square matrix with all 1s
  */
object LargestSubSquareWith1s extends App {
  val matrix = Seq(
    Seq(1, 0, 0, 1, 0, 0),
    Seq(1, 0, 1, 1, 1, 1),
    Seq(0, 1, 1, 1, 1, 0),
    Seq(1, 1, 1, 1, 1, 0),
    Seq(1, 0, 1, 1, 1, 0))

  println(s"findLargestSubSquare => ${findLargestSubSquare.orNull}")


  def findLargestSubSquare: Option[Span] = {
    val spans = for {
      py <- matrix.indices
      px <- matrix(py).indices
      subSq <- findSubSquare(px, py) if matrix(py)(px) == 1
    } yield subSq
    spans.sortBy(-_.area).headOption
  }

  def findSubSquare(px: Int, py: Int): Option[Span] = {
    var minWidth: Option[Int] = None
    var y = py
    while (y < matrix.length && matrix(y)(px) == 1) {
      val width = determineWidth(px, matrix(y))
      if (minWidth.isEmpty || minWidth.exists(_ > width)) minWidth = Some(width)
      y += 1
    }

    minWidth.map(width => Span(px, py, width, height = y - py))
  }

  def determineWidth(px: Int, row: Seq[Int]): Int = {
    var x = px
    while (x < row.length && row(x) == 1) x += 1
    x - px
  }

  case class Span(x: Int, y: Int, width: Int, height: Int) {

    def area: Int = width * height

    override def toString: String = s"(x=$x, y=$y, w=$width, h=$height), area=$area"

  }

}
