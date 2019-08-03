package com.particeep.test

object WhatsWrong1 extends App {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  /**
    * The following prints "Paris" as expected.
   */
  println(Supporter.city) //What does this print ?

  /**
    * The following prints "Ici c'est null !" because of the usage of vals and the order things get evaluated when
    * extending classes/traits in Scala. In this case 'val support' gets evaluated before 'val city' is overridden in
    * the case object Supporter as a result the value of 'val city' was captured in the 'val support' when it was still null.
    *
    * There are multiple ways to fix it:
    *   1 - using defs. When defining interfaces this is usually good practice since it gives the user of the interfaces
    *   the ability to choose to between overriding using defs or vals.
    *   2 - using lazy vals which in this case is probably better since support is never going to change after it has
    *   been evaluated correctly the first time.
    *   3 - very much not recommended but it's nice to know that the following exists in Scala :)
    *   case object Supporter extends {
    *     val city = "Paris"
    *   } with Interface
   */
  println(Supporter.support) //What does this print and why ? How to fix it ?
}
