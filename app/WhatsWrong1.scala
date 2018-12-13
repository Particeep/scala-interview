package whats_wrong

object WhatsWrong1 {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  Supporter.city //What does this print ? res0: String = Paris
  Supporter.support //What does this print and why ? How to fix it ? 
  //res1: String = Ici c'est null !
  /*
   the problem is a run-time problem, scala first interprets the 'Interface' trait, 
   and thus initializes' city 'to' null ', then the value' support 'assigns it the value' Ici c'est null ! ',
   after he goes busy with the object' Supporter 'where he will assign' Paris' to city.
   to correct the problem, it must delay the assignment of the 'support' value.
   this can be done via two methods:
   define support as a function by changing 'val' with 'def'
   or rebndre the calculated field by adding the key word 'lazy' to 'val'
   */
}
