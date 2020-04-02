package com.particeep.test

object WhatsWrong1 {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  Supporter.city //What does this print ? It print "paris"
  Supporter.support //What does this print and why ? How to fix it ?
  /**
  * This print "Ici c'est null !" because he could not know the value of field city.
   * `support` is evaluate before `city` is set. Next `city` is overriding by support is not evaluate a second time after `city` change
   *
   * You could fix it by using def for example :
   * def city: String
   * override def city = "Paris"
   */
}
