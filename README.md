# interview-prep
Interview Preparation

A collection of samples of interview/coding puzzles from various sources.

#### BracketChecker

Implement a function that returns whether a string made of different bracket characters is well formed or not.

For example:
* "{({})[]}" is a well formed bracket string
* "{[](}" is not a well formed bracket string

Needless to say any single brackets are automatically counted as not well formed

#### ClassRoom

You are in charge of a classroom which has n seats in a single row, numbered 0 through n-1.
During the day students enter and leave the classroom for the exam.
In order to minimize the cheating, your task is to efficiently seat all incoming students.

You're given 2 types of queries: add_student(student_id) -> seat index, and remove_student(student_id) -> void.

The rules for seating the student is the following:
1. The seat must be unoccupied
2. The closest student must be as far away as possible
3. Ties can be resolved by choosing the lowest-numbered seat.
  
#### Maze

Find the shortest distance through a 10x10 maze.   
  
#### PageVisits

You are given logs that contain user and page visits for a given day.
u1 -> p4
u3 -> p2
u7 -> p9
...

Come up with efficient data structure that answers these queries
  
Which page was visited by exactly 100 users in day?
Which page was visited by only one user exactly 15 times in a day?
Which page was visited by u3 more than 20 times in a day?

#### SquareRoot

Develop a function that computes the square root of a number.