package com.particeep.test

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
    val str = new StringBuilder("");
    if (params.isEmpty) {
      return str.toString();
    }

    str.append("?");
    params.foreach((e => str.append("&" + e._1 + "=" + e._2)));
    str.deleteCharAt(1);
    return str.toString();
  }


  /**
    * Test if a String is an email
    */
  def isEmail(maybeEmail: String): Boolean = {
    """(\w+)@([\w\.]+)""".r.unapplySeq(maybeEmail).isDefined;
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
  def power(i:Int, n:Int):Int = {
    if (n == 0) {
      return 1;
    }
    else if (n == 1) {
      return i;
    }
    else {
      return i * power(i, n - 1);
    }
  }


  def main(args: Array[String]): Unit = {
    println(power(2, 3));
    println(isEmail("test@gmail.com"));
    println(isEmail("te st@gmail.com"));

    val params = Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12");
    println(encodeParamsInUrl(params));
    println(encodeParamsInUrl(Map()));
  }
}
