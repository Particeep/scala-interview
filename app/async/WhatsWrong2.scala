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
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo        <- CEODao.byId(ceo_id.get)
      enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
    } yield {
      (ceo, enterprise)
    }
  }

  /*
    1. I don't really get why the parameter is an option:
     this id is either present or absent from the lists, but not the id itself.
    2. The returned type could be refactored as a Future of Option of a pair (CEO, Enterprise)
      that could also be defined as a type alias
   */
  type CEOAndEnterprise = (CEO, Enterprise)

  def getCEOAndEnterprise2(ceo_id: String): Future[Option[CEOAndEnterprise]] = {
    for {
      ceo <- CEODao.byId(ceo_id)
      enterprise <- EnterpriseDao.byCEOId(ceo_id)
    } yield (ceo, enterprise) match {
      case (Some(ceo), Some(enterprise)) =>
        Some((ceo, enterprise))
      case (_, _) =>
        None
    }
  }
}
