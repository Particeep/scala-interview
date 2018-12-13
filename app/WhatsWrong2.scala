package whats_wrong

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future { ceos.find(_.id == id) }
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.id == id) }
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.ceo_id == ceo_id) }
}
/*
Futur (<complete)> will result in the future allowing us to execute asynchronous tasks, 
and in our case the getceoandenterprise function, returns the results before the two futures 
complete their execution, one of the solutions to solve this problem and use the promises
*/
object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {

    val promise_enterprises = Promise[Option[Enterprise]]()
    val promise_ceo = Promise[Option[CEO]]()

    for {
      ceo <- promise_ceo.completeWith(CEODao.byId(ceo_id.get)).future
      enterprise <- promise_enterprises.completeWith(EnterpriseDao.byCEOId(ceo_id.get)).future
    } yield {
      (ceo, enterprise)
    }
  }
}
