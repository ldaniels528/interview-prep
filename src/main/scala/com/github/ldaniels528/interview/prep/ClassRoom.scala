package com.github.ldaniels528.interview.prep

/**
  * You are in charge of a classroom which has n seats in a single row, numbered 0 through n-1.
  * During the day students enter and leave the classroom for the exam.
  * In order to minimize the cheating, your task is to efficiently seat all incoming students.
  *
  * You're given 2 types of queries: add_student(student_id) -> seat index, and remove_student(student_id) -> void.
  *
  * The rules for seating the student is the following:
  * 1) The seat must be unoccupied
  * 2) The closest student must be as far away as possible
  * 3) Ties can be resolved by choosing the lowest-numbered seat.
  *
  * @see https://www.careercup.com/question?id=5730243296886784
  */
object ClassRoom extends App {
  private val seats: Array[Option[Student]] = (0 until 10).map(_ => None).toArray

  type Seat = Int
  type Student = Int

  add_student(1)
  add_student(2)
  add_student(3)
  add_student(5)
  add_student(6)
  show()

  remove_student(5)
  add_student(8)
  show()


  def add_student(student_id: Student): Option[Seat] = {
    findEmptySeat map { index =>
      seats(index) = Some(student_id)
      index
    }
  }

  def remove_student(student_id: Student): Boolean = {
    seats.indexWhere(_.contains(student_id)) match {
      case -1 => false
      case index =>
        seats(index) = None
        true
    }
  }

  def findEmptySeat: Option[Seat] = {
    // get the collections of occupied and unoccupied seats
    val (occupiedSeats, emptySeats) = seats.zipWithIndex.partition { case (student, _) => student.nonEmpty } match {
      case (occupied, empty) => (occupied.map(_._2), empty.map(_._2))
    }

    // if no seats are occupied, return the furthest seat back
    // otherwise, determine which seat to return
    if (occupiedSeats.isEmpty) Some(seats.indices.last)
    else {
      // determine how close each empty seat is to a seated student
      val candidateSeats = emptySeats map { emptySeat =>
        val distance = occupiedSeats.map(occupiedSeat => Math.abs(occupiedSeat - emptySeat)).min
        emptySeat -> distance
      } sortBy { case (_, dist) => -dist }

      // return the seat furthest way from other all students
      candidateSeats.headOption.map(_._1)
    }
  }

  def show(): Unit = {
    seats.zipWithIndex foreach { case (student, seat) =>
      println(f"[$seat%02d] ${student.map(id => s"Student #$id").getOrElse("---")}")
    }
    println("*" * 40)
  }

}
