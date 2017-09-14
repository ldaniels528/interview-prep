package com.github.ldaniels528.interview.scalaprep.sorting

object CountSort extends App {

  val alist = Array(9, 3, 1, 4, 5, 7, 7, 2, 2)
  println(s"BEFORE: ${alist mkString ","}")
  println(s"AFTER: ${sort(alist, 0, 10) mkString ","}")

  /**
    * Performs a counting sort
    * @param A     is a list to be sorted
    * @param digit is the digit we want to sort by
    * @param radix is the base of the number system
    * @return
    */
  def sort(A: Array[Int], digit: Int, radix: Int) = {
    // create a list B which will be the sorted list
    val B = new Array[Int](A.length)
    val C = new Array[Int](radix)

    // counts the number of occurrences of each digit in A
    for (i <- A.indices) {
      val digit_of_Ai = (A(i) / radix ** digit) % radix
      C(digit_of_Ai) = C(digit_of_Ai) + 1
      // now C[i] is the value of the number of elements in A equal to i
    }

    println(s"C: ${C mkString ","}")

    // this FOR loop changes C to show the cumulative # of digits up to that index of C
    for (j <- 1 until radix) C(j) = C(j) + C(j - 1)

    println(s"C: ${C mkString ","}")

    // here C is modifed to have the number of elements <= i
    for (m <- A.indices.reverse) { // to count down (go through A backwards)
      val digit_of_Ai = (A(m) / radix ** digit) % radix
      C(digit_of_Ai) = C(digit_of_Ai) - 1
      println(s"B(C($digit_of_Ai)) = A($m) (B(${C(digit_of_Ai)}) = A($m) = ${A(m)})")
      B(C(digit_of_Ai)) = A(m)
    }

    println(s"C: ${C mkString ","}")
    println(s"B: ${B mkString ","}")

    B
  }

  final implicit class Symbolics(val number: Int) extends AnyVal {

    @inline
    def **(exponent: Int): Int = Math.pow(number, exponent).toInt

  }

}
