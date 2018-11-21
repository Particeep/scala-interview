object MapYourMap {

  //Tell developer names by the department code

  val devNames = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  /*
  Expected result:
  Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
   */

  val namesInDepartments =  devDepartments.groupBy(_._2).mapValues(_.keys).map{case(key,value) => key -> value.flatMap(devNames.get(_)).toList}

}