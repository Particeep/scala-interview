package com.particeep.test

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future {
    Thread.sleep(10000) // Introducing a delay to prove when futures run in parallel and when they don't
    ceos.find(_.id == id)
  }
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.id == id) }
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future {
    Thread.sleep(5000) // Introducing a delay to prove when futures run in parallel and when they don't
    enterprises.find(_.ceo_id == ceo_id)
  }
}

object WhatsWrong2 extends App {

  //Review this code. What could be done better ? How would you do it ?
  /**
    * A few things look a bit wrong / unintended here:
    * 1 - '.get' on an option is usually considered evil because it will throw an exception when the option is empty.
    *   In this case passing an option as a parameter of the method feels wrong in the first place because the caller
    *   could be in charge of mapping over the option to deal with the case where it's empty.
    * 2 - Independent futures in for comprehension. Because the future are created within the for comprehension (which
    *   is just a syntactic sugar for nested flatmaps and map) even though they are independent in practice they are
    *   not going to run in parallel since the second is going to wait for the first one to complete. Creating the
    *   futures before the for comprehension would solve this problem.
    */
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo <- CEODao.byId(ceo_id.get)
      enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
    } yield {
      (ceo, enterprise)
    }
  }

  // Here is an improved version
  def getCEOAndEnterpriseImproved(ceo_id: String): Future[(Option[CEO], Option[Enterprise])] = {
    val ceoFuture = CEODao.byId(ceo_id)
    val enterpriseFuture = EnterpriseDao.byCEOId(ceo_id)

    for {
      ceo <- ceoFuture
      enterprise <- enterpriseFuture
    } yield (ceo, enterprise)
  }

  // Sequential
  val seqStart = System.currentTimeMillis()
  println(Await.result(getCEOAndEnterprise(Option("1")), Duration.Inf))
  val seqStop = System.currentTimeMillis()
  println(s"It took ${seqStop - seqStart}ms to execute getCEOAndEnterprise which proves that the futures ran sequentially!")

  // Parallel
  val parStart = System.currentTimeMillis()
  println(Option("1").map(p => Await.result(getCEOAndEnterpriseImproved(p), Duration.Inf)).getOrElse("Option was empty"))
  val parStop = System.currentTimeMillis()
  println(s"It took ${parStop - parStart}ms to execute getCEOAndEnterpriseImproved which proves that the futures ran in parallel!")
}
