package com.github.ldaniels528.interview.scalaprep

/**
  * Given an array of integers we need to move all the zeroes to the end and maintain the order of rest of the elements.
  * Needless to say it should be an in-place solution.
  */
object MoveZeroesToTheEnd extends App {

  val array = Array(10, 5, 0, 6, 0, 1, 2, 0, 0, 0, 9, 7, 11, 17, 0)

  println(s"BEFORE: ${array.mkString(", ")}")
  moveZeroesToEnd(array, _ % 2 == 0)
  println(s"AFTER: ${array.mkString(", ")}")


  /**
    * Runtime complexity: O(n)
    * Memory complexity: O(1)
    * @param array the given array
    * @param value the value to move
    */
  def moveZeroesToEnd(array: Array[Int], value: => Int = 0): Unit = {
    var p0 = 0
    var p1 = array.length - 1
    while (p1 >= 0 && array(p1) == value) p1 -= 1
    while (p0 < p1) {
      if (array(p0) == value) {
        System.arraycopy(array, p0 + 1, array, p0, p1 - p0)
        array(p1) = value
        p1 -= 1
      }
      else p0 += 1
    }
  }

  /**
    * Runtime complexity: O(n)
    * Memory complexity: O(1)
    * @param array the given array
    * @param f     a function to indicate which values to move
    */
  def moveZeroesToEnd(array: Array[Int], f: Int => Boolean): Unit = {
    var p0 = 0
    var p1 = array.length - 1
    while (p1 >= 0 && f(array(p1))) p1 -= 1
    while (p0 < p1) {
      if (f(array(p0))) {
        val value = array(p0)
        System.arraycopy(array, p0 + 1, array, p0, p1 - p0)
        array(p1) = value
        p1 -= 1
      }
      else p0 += 1
    }
  }

}
