package com.particeep.test

/**
  * Tell developer names by the department code
  * Expected result:
  * Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
  */
object MapYourMap {

  val devNames = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  // Merge and sort
  def merge(params: Map[String, String],params2:Map[String, String]): Map[String, List[String]] = {
    var mymerge = params.keys map { k => k -> List(params(k),params2(k)) }
    var mapMerge = mymerge.toMap
    var result: Map[String, List[String]]= Map()
    
    mapMerge.keys.foreach{ i =>
      var mykey = mapMerge(i)(0)
      var myList = List() ::: List(mapMerge(i)(1))
      
      if(result.contains(mykey)){
        var newupdate= result(mykey) ::: List(mapMerge(i)(1))
        result = result +(mykey-> newupdate)
      }
      else{
        result += (mykey-> myList) 
      }
    }
    var sortTabInList = result.toList.sortWith( (x,y) => x._2.length > y._2.length );
    return sortTabInList.toMap
  }

  val namesInDepartments:Map[String, List[String]] = merge(devDepartments,devNames)

} 