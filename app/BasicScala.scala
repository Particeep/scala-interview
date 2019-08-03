package com.particeep.test

import scala.annotation.tailrec

/**
  * This is basic language questions so don't use external library or build in function
  */
object BasicScala extends App {


  /**
    * Encode parameter in url format
    *
    * Example:
    *
    * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
    * output : "?sort_by=name&order_by=asc&user_id=12"
    *
    * input  : Map()
    * output : ""
    */
  def encodeParamsInUrl(params: Map[String, String]): String =
    if (params.isEmpty) "" else params.map{case (k, v) => s"$k=$v"}.mkString("?", "&", "")


  //This is the simplest email pattern regex ever but I believe the purpose of this exercise is more about how regexes can be used nicely in Scala
  val emailRegex = """.+\@.+\..+""".r
  /**
    * Test if a String is an email
    */
  def isEmail(maybeEmail: String): Boolean = emailRegex.pattern.matcher(maybeEmail).matches()


  /**
    * Compute i ^ n
    *
    * Example:
    *
    * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
    * output : 8
    *
    * input : (i = 99, n = 38997)
    * output : 1723793299 really? This looks way too small. Even using Longs instead of Ints it massively overflows..
    */
  def power(i:Long, n:Long):Long = powerAcc(i, 1, n)

  @tailrec def powerAcc(i:Long, acc: Long, n:Long): Long = if (n == 0) i else powerAcc(i * acc, i, n - 1)


  println(encodeParamsInUrl(Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")))
  println(encodeParamsInUrl(Map()))

  val validEmailAddress = "John.Doe@particeep.com"
  println(s"Is $validEmailAddress a valid email address? ${isEmail(validEmailAddress)}")
  val invalidEmailAddress = "complete.nonsense"
  println(s"Is $invalidEmailAddress a valid email address? ${isEmail(invalidEmailAddress)}")

  println(power(2, 3))
  println(power(99, 38997))
}
