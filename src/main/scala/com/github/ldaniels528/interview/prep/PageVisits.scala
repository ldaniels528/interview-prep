package com.github.ldaniels528.interview.prep

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.util.Random

/**
  * You are given logs that contain user and page visits for a given day.
  * u1 -> p4
  * u3 -> p2
  * u7 -> p9
  * ...
  * Come up with efficient data structure that answers these queries
  *
  * Which page was visited by exactly 100 users in day?
  * Which page was visited by only one user exactly 15 times in a day?
  * Which page was visited by u3 more than 20 times in a day?
  *
  * @see https://www.careercup.com/question?id=6246739957776384
  */
object PageVisits extends App {
  private val random = new Random()
  private val domains = Seq(
    "www.amazon.com",
    "www.bing.com",
    "maps.google.com",
    "www.google.com",
    "www.hotbot.com",
    "www.n4g.com",
    "www.yahoo.com"
  )
  private val pageViews = generateData(totalUsers = 10, totalPageViews = 5000)

  // Which page was visited by exactly 100 users in day?
  val viewsByPageAndDate = pageViews.groupBy(pv => (pv.page, pv.date))
  viewsByPageAndDate.find { case (_, views) =>
    views.length >= 100 // == 100 // exact matches with random data is problematic
  } foreach { case ((page, date), views) =>
    println(s"Page '$page' has exactly 100 (${views.length}) times on $date")
  }

  // Which page was visited by only one user exactly 15 times in a day?
  val viewsByUsersPageAndDate = pageViews.groupBy(pv => (pv.user, pv.page, pv.date))
  viewsByUsersPageAndDate.find { case (_, views) =>
    views.length >= 15 // === 15 // exact matches with random data is problematic
  } foreach { case ((user, page, _), views) =>
    println(s"User '$user' visited '$page' exactly 15 (${views.length}) times")
  }

  // Which page was visited by u3 more than 20 times in a day?
  viewsByUsersPageAndDate.find { case ((user, _, _), views) =>
    user == "u3" && views.length >= 20
  } foreach { case ((user, page, date), views) =>
    println(s"User '$user' visited '$page' at least 20 (${views.length}) times on $date")
  }

  private def generateData(totalUsers: Int, totalPageViews: Int) = {
    val sdf = new SimpleDateFormat("MM/dd/yyyy")
    val users = (1 to totalUsers).map(n => s"u$n")
    for {
      _ <- 1 to totalPageViews
      user = users(random.nextInt(users.length))
      page = s"http://${domains(random.nextInt(domains.length))}"
      date = {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -random.nextInt(2))
        sdf.format(cal.getTime)
      }
    } yield PageView(user, page, date)
  }

  case class PageView(user: String, page: String, date: String)

}
