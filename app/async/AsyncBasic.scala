package com.particeep.test.async

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
    val result = for {
      result1 <- Webservice1
        .call(id)
        .flatMap {
          case Some(value1) => Future.successful(value1)
          case None => throw ValueNotFoundException(s"Error when retrieving id=$id from Webservice1")
        }
      result2 <- Webservice2
        .call(id)
        .map(_.toOption)
        .flatMap {
          case Some(value2) => Future.successful(value2)
          case None =>  throw ValueNotFoundException(s"Error when retrieving id=$id form Webservice2")
        }
    } yield result1 + result2

    result.recoverWith {
      case t: Throwable => Future.failed(t)
    }
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

final case class ValueNotFoundException(msg: String) extends RuntimeException(msg)