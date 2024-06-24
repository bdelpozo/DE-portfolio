package de.portfolio
package BasicExamples

object BasicExamples extends App {
  // If you need a piece of code that can be run, you can implement a Scala Object called "Something extends App {}"
  // Now you can run this object.
  // Let me show a serie of basic examples in order to go into it.

  // Variables, first functions and control sentences.

  type Day = Int
  type Month = Int
  type Year = Int
  type Date = (Day, Month, Year)

  val today: Date = (13, 6, 2024)
  println(s"Today is the day ${today._1}, of the month ${today._2} of the year ${today._3}")

  // This may be the most disappointing way to display a date you've ever seen. Let's enhance it a little bit:

  // match for months number --> name
  val monthName: String = today._2 match {
    case 1 => "January"
    case 2 => "February"
    case 3 => "March"
    case 4 => "April"
    case 5 => "May"
    case 6 => "June"
    case 7 => "July"
    case 8 => "August"
    case 9 => "September"
    case 10 => "October"
    case 11 => "November"
    case 12 => "December"
  }

  // now we can say that...
  println(s"Today is the day ${today._1}, of ${monthName} of ${today._3}")

  // but we can also do better. For example, let's implement st, nd, rd and th

  def addDatePreffix(day: Day): String = {
    var preffix = " "
    if (day == 1 || day == 21 || day == 31) preffix = "st"
    else if (day == 2 || day == 22) preffix = "nd"
    else if (day == 3 || day == 23) preffix = "rd"
    else preffix = "th"
    preffix
  }

  println(s"Today is ${monthName} ${today._1}${addDatePreffix(today._1)}, ${today._3}")

  // Although this seems to be better, anybody could say that this is bad Python code embedded in Scala code.

  // First I am going to create a "dictionary" for months from two list
  val monthNames = List("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
  val monthNumbers = for (n <- 1 to 12) yield n
  val myMonths = monthNames.zip(monthNumbers)

  // Now we have a list of tuples, each corresponding to each month, with its number and its name
  println(myMonths)
  // This way I can skip the match setences, even it's useful sometimes, it is better to do something else this case

  // After this, lets improve the preffix function. Let's use Functional Programming rules

  // We define three functions to validate the day and add the preffix if corresponds.
  // We use option because it helps us here to handle with the values

  def isSt(day: Day): Option[String] = if (day.toString.endsWith("1") && day != 11) Some("st") else None

  def isNd(day: Day): Option[String] = if (day.toString.endsWith("2") && day != 12) Some("nd") else None

  def isRd(day: Day): Option[String] = if (day.toString.endsWith("3") && day != 13) Some("rd") else None

  // With the implementation we are going to do of addPreffixtoDate, we can skip the function validate if it's th:
  println(isSt(21))
  println(isNd(21))
  println(isRd(21))

  // we will use getOrElse in an iterative way
  def addPreffixtoDate(day: Day): String = isSt(day).getOrElse(isNd(day).getOrElse(isRd(day).getOrElse("th")))
  println(s"21${addPreffixtoDate(21)}")
  println(s"22${addPreffixtoDate(22)}")
  println(s"23${addPreffixtoDate(23)}")
  println(s"24${addPreffixtoDate(24)}")
  // So we can implement it in our example printing today's date this way:
  println(s"Today is ${monthName} ${today._1}${addPreffixtoDate(today._1)}, ${today._3}")

}
