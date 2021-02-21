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
  def encodeParamsInUrl(params: Map[String, String]): String = 
    params.foldLeft("?")( (acc, kv) => acc + kv._1 + "=" + kv._2 +"&").dropRight(1)
    //there is an additional '&' at the end that i have to delete with dropRight(1)

  /**
    * Test if a String is an email
    */
def isEmail(maybeEmail: String): Boolean = """^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$""".r.unapplySeq(maybeEmail).isDefined  


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
  def power(i:Int, n:Int):Int = 
  (i,n) match {
    case (0,_) => 0
    case (_,0) => 1
    case (_,1) => i
    case (_,n) => if (n % 2 == 0) { ( power (i,(n / 2)) ) * ( power (i,(n / 2)) )} 
                  else { i * ( power (i, ((n - 1) / 2))) * ( power (i, ((n - 1) / 2)) )}
  }

  def main(args: Array[String]) = {
    //println(encodeParamsInUrl(Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")))
    //println(isEmail("anis@gmailfr"))
    //println(power(99,38997))
   }
}
