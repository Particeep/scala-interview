package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future(ceos.find(_.id == id))
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id:        String): Future[Option[Enterprise]] = Future(enterprises.find(_.id == id))
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future(enterprises.find(_.ceo_id == ceo_id))
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?

  /*FOR ME:

  ceo_id.get is an obvious None.get generator :-)
  I'll just add option control code and keep the function signature the closest as intended

   */
/*
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo        <- CEODao.byId(ceo_id.get)
      enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
    } yield {
      (ceo, enterprise)
    }
  }
*/

  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    if (ceo_id.isDefined) {
      for {
        ceo <- CEODao.byId(ceo_id.get)
        enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
      } yield {
        (ceo, enterprise)
      }
    } else Future.failed(new Exception("Empty ceo_id!"))
  }


}
