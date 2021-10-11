package com.particeep.test.basic

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
    params.size match {
      case 0 => ""
      case _ => s"?${params.map( x => { s"${x._1}=${x._2}" }).mkString("&")}"
    }
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = {
    val regexp = """^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
    maybeEmail match {
      case null => false
      case email if email.trim.isEmpty => false
      case email if regexp.findFirstIn(email).isDefined => true
      case _ => false
    }
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
    n match {
      case n if n < 0 => 1 / power(i, -n)
      case 0 => 1
      case 1 => i
      case n if n % 2 == 0 => {
        val s  = power(i, n/2)
        s * s
      }
      case n => i * power(i, n - 1)
    }
  }
}
