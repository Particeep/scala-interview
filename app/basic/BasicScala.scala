package com.particeep.test.basic

import scala.collection.immutable.{AbstractMap, SeqMap, SortedMap}

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
    def encode(params: Map[String, String], acc: String): String = {
      if(params.isEmpty) acc.substring(0, acc.length-1)
      else {
        encode(params.tail, acc + params.head._1 + "=" + params.head._2 + "&")
      }
    }

    if(params.isEmpty) ""
    else encode(params, "?")
  }
  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = {
    val emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,3}$"
    maybeEmail.matches(emailRegex)
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
    def power(x: Int, y: Int, acc: Int): Int = {
      if(y == 0) acc
      else {
        power(x, y-1, acc*x)
      }
    }

    power(i, n, 1)
  }

}
