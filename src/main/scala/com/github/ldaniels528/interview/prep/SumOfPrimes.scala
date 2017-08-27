package com.github.ldaniels528.interview.prep

/**
  * Sum Of Primes
  * I. Get the sum of all prime numbers up to N. primeSum(N).
  *
  * Follow-up: If primeSum(N) is frequently called, how to optimize it.
  */
object SumOfPrimes extends App {

  val myNumber = 1001
  println(s"sum = ${primeSum(myNumber)}")

  def primeSum(number: Int): Int = {
    var sum = 0
    for (n <- 2 to number) {
      if (isPrime(n)) {
        //println(s"prime: $n")
        sum += n
      }
    }
    sum
  }

  def isPrime(number: Int): Boolean = number match {
    case n if n < 2 => false
    case 2 => true
    case _ =>
      var n = 1
      while (n < number / n) {
        n += 1
        if (number % n == 0) return false
      }
      true
  }

}
