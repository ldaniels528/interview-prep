package com.github.ldaniels528.interview.scalaprep

import scala.language.postfixOps

/**
  * Given an NxM matrix, find the largest sub-square matrix with all 1s
  */
object LargestSubSquareWithOnes extends App {
  val matrix = Seq(
    Seq(1, 0, 0, 1, 0, 0),
    Seq(1, 0, 1, 1, 1, 1),
    Seq(0, 1, 1, 1, 1, 0),
    Seq(1, 1, 1, 1, 1, 0),
    Seq(1, 0, 1, 1, 1, 0))

  println(s"findLargestSubSquare => ${findLargestSubSquare.orNull}")


  def findLargestSubSquare: Option[Span] = {
    var spans: List[Span] = Nil
    var py = 0
    while (py < matrix.length) {
      var px = 0
      while (px < matrix(py).length) {
        if (matrix(py)(px) == 1) {
          findSubSquare(px, py) foreach { span =>
            spans = span :: spans
            px += span.width - 1
          }
        }
        px += 1
      }
      py += 1
    }
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
