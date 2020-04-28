package com.particeep.test

object WhatsWrong1 {

  trait Interface {
    def city: String
    def support: String = s"Ici c'est $city !"

    /* Replace val with def
    * just define the methods in your trait that you want to implement the extension classes 
    * otherwise it will retain a property or method local to the class or to the trait
    */

    /*
    val city: String
    val support: String = s"Ici c'est $city !"
    */
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  Supporter.city //What does this print ?   ----------------------------> Paris: String
  Supporter.support //What does this print and why ? How to fix it ?----> Ici c'est null !: String
}
