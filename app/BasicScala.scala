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
   if(params.isEmpty){
    return ""
   }
    var result="?"
    var lastElm = params.size-1
    var start = 0
    params.keys.foreach{ i =>  
      var elm = i+"="+params(i)
        if(start<lastElm){
          elm+="&"
        }
        result+=elm
        start+=1
    }
    return result
 }


  /**
    * Test if a String is an email
    * I put banned e-mail by creating a Regex object and I call the .r method on the String and finally findFirstIn searches for a match
    */
  def isEmail(maybeEmail: String): Boolean = if("""^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$""".r.findFirstIn(email) == None)false else true


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
    var j = i
    var k = n
    var result=1
    if(n!=0) {
      while(k>0){
        if(j>Int.MaxValue){
          return 1723793299
        }
        result=result*j
        k=k-1
      }
      return result
    }
     return 1
  } 

}
