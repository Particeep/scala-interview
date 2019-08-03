package com.particeep.test

/**
  * Tell developer names by the department code
  * Expected result:
  * Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
  */
object MapYourMap extends App {

  val devNames = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  val namesInDepartments:Map[String, List[String]] = {
    val depToDevList = for {
      (devKey1, dep) <- devDepartments.toList
      (devKey2, devName) <- devNames if devKey1 == devKey2
    } yield (dep, devName)
    depToDevList.groupBy(_._1).mapValues(_.map(_._2))
  }

   println(namesInDepartments)
}
