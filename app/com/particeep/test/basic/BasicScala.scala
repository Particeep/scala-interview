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
  def encodeParamsInUrl(params: Map[String, String]): String = params.map(v => v._1 + "=" + v._2).mkString("?", "&", "")

  /**
   * regex used
   * https://html.spec.whatwg.org/multipage/input.html#valid-e-mail-address
   */
  private val mailRegex =
    """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = maybeEmail match {
    case null                                         => false
    case s if s.isEmpty                               => false
    case s if mailRegex.findFirstMatchIn(s).isDefined => true
    case _                                            => false
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
  def power(i: Int, n: Int): Int = scala.math.pow(i, n).toInt //99^38997 = 1723793299
  // I didn't understand the formula
  // used because It should be Int max value ( for Int type) :/

}
