package com.particeep.test

import scala.annotation.tailrec

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
  def encodeParamsInUrl(params: Map[String, String]): String =
    params match {
      case map if map.isEmpty => ""
      case _                  => params.map { case (key, valeur) => s"$key=$valeur" }.mkString("?", "&", "")
    }

  /**
   * Test if a String is an email
   */
  val r                                    = """/^\S+@\S+\.\S+$/""".r
  def isEmail(maybeEmail: String): Boolean = r.pattern.matcher(maybeEmail).matches()

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299 => 6.166639011839903274328675267873954E+77821
   */
  def power(i: Int, n: Int): BigDecimal = puissance(1, n)(i)

  @tailrec
  def puissance(previous: BigDecimal, interation: Int)(implicit init: Int): BigDecimal =
    interation match {
      case 1 => previous
      case _ => puissance(previous * init, interation - 1)
    }

}
