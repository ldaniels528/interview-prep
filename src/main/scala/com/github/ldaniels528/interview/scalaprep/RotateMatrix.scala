package com.github.ldaniels528.interview.scalaprep

/**
  * Write an algorithm to rotate a matrix
  */
object RotateMatrix extends App {
  private val matrix = Array(
    Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
    Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 8),
    Array(2, 3, 4, 5, 6, 7, 8, 9, 0, 7),
    Array(3, 4, 5, 6, 7, 8, 9, 0, 1, 6),
    Array(4, 5, 6, 7, 8, 9, 0, 1, 2, 5),
    Array(5, 6, 7, 8, 9, 0, 1, 2, 3, 4),
    Array(6, 7, 8, 9, 0, 1, 2, 3, 4, 3),
    Array(7, 8, 9, 0, 1, 2, 3, 4, 5, 2),
    Array(8, 9, 0, 1, 2, 3, 4, 5, 6, 1),
    Array(9, 8, 7, 6, 5, 4, 3, 2, 1, 0))

  rotate90()
  show(matrix)

  def rotate90(): Unit = {
    for(x <- matrix.indices.reverse) {
      for(y <- matrix.indices) {
        val z = matrix.indices.last - x
        val temp = matrix(x)(y)
        matrix(x)(y) = matrix(y)(z)
        matrix(y)(z) = temp
      }
    }
  }

  def show(matrix: Array[Array[Int]]): Unit = {
    matrix foreach { row =>
      println(row mkString " ")
    }
  }

}
