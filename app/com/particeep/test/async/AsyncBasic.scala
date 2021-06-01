package com.particeep.test.async

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AppError extends Throwable
case class NotFound(msg: String) extends AppError

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String): Future[Long] = for {
    r1 <- Webservice1.call(id).flatMap(handleError)
    r2 <- Webservice2.call(id).flatMap(handleError)
  } yield r1 + r2

  private def handleError(maybeInt: Option[Int]): Future[Long]         = maybeInt match {
    case Some(value) => Future.successful(value.toLong)
    case None        => Future.failed(NotFound("Int not found")) //I would use ADT for a real use case
  }
  private def handleError(maybeInt: Either[String, Int]): Future[Long] = maybeInt match {
    case Right(value) => Future.successful(value.toLong)
    case Left(err)    => Future.failed(NotFound(err)) //I would use ADT for a real use case
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
