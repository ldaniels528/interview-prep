package com.github.ldaniels528.interview.scalaprep.sorting

import com.github.ldaniels528.interview.scalaprep.util.ComparableHelper._

import scala.util.Random

/**
  * Binary Search
  */
object BinarySearch extends App {

  val numbers = (0 to 100000 by 10).map(v => v: Integer)
  val number = numbers(Random.nextInt(numbers.length))

  numbers.searchFor(number) match {
    case Some(index) =>
      println(s"$number is at index $index")
    case None =>
      println(s"$number was not found")
  }

  final implicit class BinarySearchExtensions[T <: Comparable[T]](val values: Seq[T]) extends AnyVal {

    /**
      * Performs a binary search
      * Lookup complexity: O(log n)
      * @param value the value to search for
      * @return
      */
    @inline
    def searchFor(value: T): Option[Int] = {
      var p0 = 0
      var p1 = values.length - 1
      var changed = true

      while (p0 != p1 && values(p0) < value && values(p1) > value && changed) {
        val mp = (p0 + p1) / 2
        println(s"$value: mp = $mp (${values(mp)}), p0 = $p0 (${values(p0)}), p1 = $p1 (${values(p1)})")

        val z0 = p0
        val z1 = p1
        if (value >= values(mp)) p0 = mp else p1 = mp
        changed = z0 != p0 || z1 != p1
      }

      if (values(p0) == value) Some(p0)
      else if (values(p1) == value) Some(p1)
      else None
    }
  }

}
