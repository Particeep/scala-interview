package whats_wrong

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai"))

  def byId(id: String): Future[Option[CEO]] = Future { ceos.find(_.id == id) }
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2"))

  def byId(id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.id == id) }
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.ceo_id == ceo_id) }
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo <- CEODao.byId(ceo_id.get)
      enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
    } yield {
      (ceo, enterprise)
    }
  }

}

/*
1- in this code we have all the data present so we dont need to use Future, but i suppose that in the real case we get the data from database, so we will need to use Future
2- in strings we should use equals unstead of ==
3- in getCEOAndEnterprise methode, it is not recommanded to use ceo_id.get, if the value of ceo_id is None, we will have an exception, in my case i will write the function as bellow

 def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    ceo_id.map( id => 
      for {
        ceo <- CEODao.byId(id)
        enterprise <- EnterpriseDao.byCEOId(id)
      } yield {
        (ceo, enterprise)
      }
    ).getOrElse(Future(None, None))
  }


*/ 