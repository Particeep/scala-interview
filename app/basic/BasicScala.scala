package com.particeep.test.basic

import scala.annotation.tailrec


/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

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
  def encodeParamsInUrl(params: Map[String, String]): String = {
    if (params.nonEmpty)
      params.map(v=> v._1 + "=" + v._2).mkString("?","&","")
    else
      ""
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = {
    val emailValidator = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)".r
    emailValidator.pattern.matcher(maybeEmail).matches()
  }

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299
   */

    def power(i: Int, n: Int): Int = {
      powerAcc(i,n-1,i)
    }
    @tailrec
    def powerAcc(i:Int,n:Int, acc : Int): Int = {
      if (n==0) acc
        else
    powerAcc(i, n - 1, acc * i)
    }
}
