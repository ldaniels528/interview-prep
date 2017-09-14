package com.github.ldaniels528.interview.scalaprep

object StringPermutations extends App {

  val s1 = "Hello World!!"
  val s2 = "olleH! World!"

  println(s"Are '$s1' and '$s2' permutation? ${isPermutation(s1, s2)}")

  /**
    * Determines whether the two strings are permutations of one another
    * runtime complexity: O(3n) => O(n)
    * memory complexity: O(1)
    * @param s1 string 1 of 2
    * @param s2 string 2 of 2
    * @return true, if the two strings are permutations of one another
    */
  def isPermutation(s1: String, s2: String): Boolean = {
    if (s1.length != s2.length) false
    else {
      val a1 = new Array[Int](256)
      val a2 = new Array[Int](256)
      s1.foreach(a1(_) += 1)
      s2.foreach(a2(_) += 1)

      for (n <- a1.indices) {
        if (a1(n) != a2(n)) return false
      }
      true
    }
  }

}
