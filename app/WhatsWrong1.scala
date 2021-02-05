package com.particeep.test

object WhatsWrong1 {

  trait Interface {
    def city: String //val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    //override val city = "Paris"
    override def city = "Paris"
  }

  Supporter.city //What does this print ? (it prints : "Paris")
  //Supporter.support //What does this print and why ? How to fix it ? (it prints : "Ici c'est null !", 
  //the reason behind this is that the $city in the variable support is evaluated when definied with the interface variable
  //city and not with the overrided one from the object Supporter )
  // fix : use def instead of val 
  // val : evaluates when defined.
  // def : evaluates on every call.
}
