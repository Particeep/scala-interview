package com.particeep.test

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

  /**
   * We should never use .get.
   * You have to check ceo_id and make a case if he is not set by returning Future[(None, None)] for exemple.
   *
   * Actually you use a for comprehension.
   * EnterpriseDao.byCEOId will wait the result of CEODao.byId but you could make a parralele call to be more efficient
   * because you doesn't use the result of first call to make the second call.
   *
   * In your case, the more easy to fix it, is to do not use future, because they juste add complexity in simple code.
   */
}
