package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)
case class CEOEntreprise(ceo_id: String, first_name:String,  last_name: String, enterprise_id: String, name: String)

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
    Enterprise("2", "Facebook", "2"),
    Enterprise("3", "Instagram", "1")
  )

  def byId(id:        String): Future[Option[Enterprise]] = Future(enterprises.find(_.id == id))
  def byCEOId(ceo_id: String): Future[List[Enterprise]]   = Future(enterprises.filter(_.ceo_id == ceo_id))
}

object EnterpriseAndCEODao {
  val ceosEnterprises = List(
    CEOEntreprise("1", "Mark", "Zuckerberg", "1", "Google"),
    CEOEntreprise("2", "Sundar", "Pichai", "2", "Facebook"),
  )

  def byCEOId(id:        String): Future[Option[CEOEntreprise]] = Future(ceosEnterprises.find(_.ceo_id == id))
  def byEnterpriseId(id: String): Future[Option[CEOEntreprise]] = Future(ceosEnterprises.find(_.enterprise_id == id))
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?
  // There is a possibility to have a None.get Error in function getCEOAndEnterprise and getCEOAndEnterpriseOCO
  // I would test if ceo_id is defined and if not return en Exception
  // We could join the two list by ceo_id and create a CEOEntrepriseDAO (ie in function getCEOAndEnterpriseOCO)
  // Or
  // I think this could generate an exception if a CEO is CEO of more than one Enterprise
  // The find method return the first element in the collection which match the predicate
  // I would use filter instead of find

  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], List[Enterprise])] = {
    if(ceo_id.isDefined) {
      for {
        ceo        <- CEODao.byId(ceo_id.get)
        enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
      } yield {
        (ceo, enterprise)
      }
    } else
      Future.failed(new Exception("the field ceo_id is not defined"))
  }

  def getCEOAndEnterpriseOCO(ceo_id: Option[String]): Future[(Option[CEOEntreprise])] = {
    if(ceo_id.isDefined) {
      for {
        ceoEnterprise <- EnterpriseAndCEODao.byEnterpriseId(ceo_id.get)
      } yield {
        (ceoEnterprise)
      }
    } else
      Future.failed(new Exception("the field ceo_id is not defined"))
  }
}
