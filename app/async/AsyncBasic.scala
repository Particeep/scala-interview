package com.particeep.test.async

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String): Future[Int] = {

    val fResult = for {
      ws1 <- Webservice1.call(id).flatMap {
        case None => Future.failed(new Exception("WS1 failed"))
        case Some(value) => Future.successful(value)
      }
      ws2 <- Webservice2.call(id).map(_.toOption).flatMap {
        case None => Future.failed(new Exception("WS2 failed"))
        case Some(value) => Future.successful(value)
      }
    } yield ws1 + ws2

    fResult

    /*   fResult.onComplete {
      case Failure(exception) => { println(s"Exception: $exception"); None }
      case Success(value) => { println(Some(value)); Some(value) }
    }*/
  }
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
