package whats_wrong

object WhatsWrong1 {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

    Supporter.city //What does this print ?
    Supporter.support//What does this print and why ? How to fix it ?
  
  /*
	Supporter.city will print : Paris , because it is overrided
	Supporter.support will print : Ici c'est null ! , because the support is declared as val, his value will never change from the first 
	instantiation, to fix the problem we have to declare support as lazy val, so she take the value when we call it for the first time.
  */
}
