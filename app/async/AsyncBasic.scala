package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String) : Future[Int] =
    for {
      ws1 <- Webservice1.call(id).flatMap {
        case None => Future.failed(new Exception("Compute failed for Webservice 1"))
        case a:Option[Int] => Future.successful(a.get)
      }
      ws2 <- Webservice2.call(id).flatMap{
        case Left(_) => Future.failed(new  Exception("Compute failed for Webservice 2"))
        case Right(value) => Future.successful(value)
      }
    }yield ws1+ws2
}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
