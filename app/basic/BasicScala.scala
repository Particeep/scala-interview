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
    if (params.isEmpty) "" else params.map {case (p,v) => { s"$p=$v" } }.mkString("?","&","")
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = {
    val emailRegex = """.+\@.+\..+""".r
    emailRegex.pattern.matcher(maybeEmail).matches()
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

  // Naive implementation with INT. for i=99 n=38997 Ints are largely to small. For large value we should use BigInt and tail rec implementation if necessary.

  def power(i: Int, n: Int): Int = {
    (i,n) match {
      case (0,_) => 0
      case (_,0) => 1
      case (i,n) => i*(power(i,n-1))
    }
  }

}
