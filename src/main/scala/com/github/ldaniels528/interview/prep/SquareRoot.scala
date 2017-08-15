package com.github.ldaniels528.interview.prep

import scala.util.Random

/**
  * Computes the square root of a number
  */
object SquareRoot extends App {
  private val random = new Random()
  private val value = args.headOption.map(_.toInt).getOrElse(random.nextInt(1500) + 1)

  println(s"The sqrt($value) is ${sqrt(value)}")

  def sqrt(number: Double, accuracy: Double = 1e-14): Double = {
    var n: Double = number match {
      case x if x >= 1000 => 30
      case x if x >= 100 => 10
      case _ => 0
    }
    var increment: Double = 1
    do {
      while (pow2(n + increment) <= number) n += increment
      increment *= 0.1
    } while (pow2(n) < number && increment >= accuracy)
    n
  }

  private def pow2(n: Double): Double = n * n

}
