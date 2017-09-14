package com.github.ldaniels528.interview.scalaprep.sorting

import scala.language.postfixOps

object MinHeap extends App {

  System.out.println("The Min heap is ")
  val minHeap = new MinHeap(15)
  minHeap.insert(5)
  minHeap.insert(3)
  minHeap.insert(17)
  minHeap.insert(10)
  minHeap.insert(84)
  minHeap.insert(19)
  minHeap.insert(6)
  minHeap.insert(22)
  minHeap.insert(9)
  minHeap.minHeap()
  minHeap.print()

  System.out.println("The 1st Min val is " + minHeap.remove())
  System.out.println("The 2nd Min val is " + minHeap.remove())
  System.out.println("The 3rd Min val is " + minHeap.remove())

}

class MinHeap(maxSize: Int) {
  private var size = 0
  private val FRONT = 1
  private var heap = new Array[Int](2 * maxSize + 1)
  heap(0) = Integer.MIN_VALUE

  def insert(element: Int): Unit = {
    size += 1
    heap(size) = element

    var current = size
    while (heap(current) < heap(parentIndex(current))) {
      swap(current, parentIndex(current))
      current = parentIndex(current)
    }
  }

  def print(): Unit = {
    var i = 1
    while (i <= size / 2) {
      System.out.print(" PARENT : " + heap(i) + " LEFT CHILD : " + heap(2 * i) + " RIGHT CHILD :" + heap(2 * i + 1))
      System.out.println()
      i += 1
    }
  }

  def minHeap(): Unit = {
    var pos = size / 2
    while (pos >= 1) {
      minHeapify(pos)
      pos -= 1
    }
  }

  def remove(): Int = {
    val popped = heap(FRONT)
    heap(FRONT) = heap(size)
    size -= 1
    minHeapify(FRONT)
    popped
  }

  private def isLeaf(pos: Int): Boolean = pos >= (size / 2) && pos <= size

  private def leftChildIndex(pos: Int) = 2 * pos

  private def rightChildIndex(pos: Int) = (2 * pos) + 1

  private def parentIndex(pos: Int) = pos / 2

  private def swap(index0: Int, index1: Int): Unit = {
    val tmp = heap(index0)
    heap(index0) = heap(index1)
    heap(index1) = tmp
  }

  private def minHeapify(pos: Int): Unit = {
    if (!isLeaf(pos) && heap(pos) > heap(leftChildIndex(pos)) || heap(pos) > heap(rightChildIndex(pos))) {
      if (heap(leftChildIndex(pos)) < heap(rightChildIndex(pos))) {
        swap(pos, leftChildIndex(pos))
        minHeapify(leftChildIndex(pos))
      } else {
        swap(pos, rightChildIndex(pos))
        minHeapify(rightChildIndex(pos))
      }
    }
  }

}
