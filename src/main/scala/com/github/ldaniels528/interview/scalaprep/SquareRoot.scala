package com.github.ldaniels528.interview.scalaprep

/**
 * Computes the square root of a number
 */
object SquareRoot extends App {

  def sqrt(number: Double, accuracy: Double = 1e-14): Double = {
    var n: Double = 0
    var increment: Double = 1
    while (pow2(n) < number && increment >= accuracy) {
      while (pow2(n + increment) < number) n += increment
      increment *= .1
    }
    n
  }

  private def pow2(n: Double): Double = n * n

  val value = 101
  println(s"The sqrt($value) is ${sqrt(value)} (${pow2(sqrt(value))})")

}
