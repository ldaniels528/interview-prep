package com.github.ldaniels528.interview.scalaprep

/**
  * Write an algorithm to compute the derivative of a polynomial
  */
object DerivativeOfPolynomial extends App {

  // the polynomial is 3x^2 + 5x + 5 -> 6x + 5

  val polynomial = Polynomial(3, 5, 5)

  println(s"The derivative of $polynomial is ${polynomial.derivative}")


  case class Polynomial(coefficients: Int*) {

    def derivative: Polynomial = {
      Polynomial(coefficients.dropRight(1).zipWithIndex map { case (coeff, index) =>
        val exp = (coefficients.length - index) - 1
        coeff * exp
      }: _*)
    }

    override def toString: String = {
      coefficients.zipWithIndex map { case (coeff, index) =>
        (coefficients.length - index) - 1 match {
          case 0 => coeff.toString
          case 1 => s"${coeff}x"
          case exp => s"${coeff}x^$exp"
        }
      } mkString " + "
    }

  }

}
