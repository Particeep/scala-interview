package com.particeep.test
import scala.collection.mutable

/**
  * Tell developer names by the department code
  * Expected result:
  * Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
  */
object MapYourMap {

  val devNames = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  def mergeMaps(names: Map[String, String], departments: Map[String, String]): Map[String, List[String]] = {
    val result = scala.collection.mutable.Map[String, List[String]]();
    val values = mutable.ListBuffer[String]();

    for (e2 <- departments) {
      for (e1 <- names) {
        if (e2._1.equals(e1._1)) {
          values += e1._2;
        }
      }
      //if value with same key exists
      if (!result.keys.exists(_ == e2._2)) {
        result(e2._2) = result(e2._2) ++ values.toList;
      }
      else {
        result += (e2._2 -> values.toList);
      }
      values.clear();
    }
    return result.toMap;
  }

  val namesInDepartments:Map[String, List[String]] = mergeMaps(devNames, devDepartments)
}
