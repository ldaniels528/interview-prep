package com.github.ldaniels528.interview.scalaprep.sorting

/**
  * Quicksort (sometimes called partition-exchange sort) is an efficient sorting algorithm, serving as a systematic
  * method for placing the elements of an array in order. Developed by Tony Hoare in 1959[1] and published in 1961,
  * it is still a commonly used algorithm for sorting. When implemented well, it can be about two or three times
  * faster than its main competitors, merge sort and heapsort.
  */
object QuickSorting extends App {

  val alist = Array(9, 3, 1, 4, 5, 7, 7, 2, 2)

  println(s"BEFORE: ${alist mkString ","}")
  quickSort(alist)
  println(s"AFTER: ${alist mkString ","}")

  def quickSort(array: Array[Int]): Unit = {

    def sort(A: Array[Int], lo: Int, hi: Int): Unit = {
      if (lo < hi) {
        val p = partition(A, lo, hi)
        sort(A, lo, hi = p - 1)
        sort(A, lo = p + 1, hi)
      }
    }

    sort(array, lo = 0, hi = array.length - 1)
  }

  def partition(array: Array[Int], lo: Int, hi: Int): Int = {
    val pivot = array(hi)
    var i = lo - 1
    for (j <- lo until hi) {
      if (array(j) < pivot) {
        i += 1
        swap(array, i, j)
      }
    }
    if (array(hi) < array(i + 1)) swap(array, i + 1, hi)
    i + 1
  }

  def swap(array: Array[Int], i: Int, j: Int): Unit = {
    val temp = array(i)
    array(i) = array(j)
    array(j) = temp
  }

}
