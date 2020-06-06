package com.particeep.test

object WhatsWrong1 {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  def main(args: Array[String]): Unit = {
    println(Supporter.city); //What does this print ? => It prints 'Paris'
    println(Supporter.support); //What does this print => It prints 'Ici c'est null !'
    /**
      why? => this is caused by the strict evaluation (evaluation at compile time)
              of 'support'. In the abscence of initialization of 'city' in 'Interface',
              its value is set to null

      How to fix it ? => the best solution would be to declare 'support' as a lazy val
                         so that it will take the overriden 'city' value at execution time ("Paris")
    */
  }
}
