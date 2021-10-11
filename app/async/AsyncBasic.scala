package com.particeep.test.async

import java.util.concurrent.TimeUnit
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, FiniteDuration}

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String): Int = {

    val futureWebService1 = Webservice1.call(id)
    val futureWebService2 = Webservice2.call(id)

    val result: Future[(Option[Int], Either[String, Int])] = for {
      ws1 <- futureWebService1
      ws2 <- futureWebService2
    } yield (ws1, ws2)

    val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
    val resCompute = Await.result(result, maxWaitTime) match {
      case (Some(res1), Right(res2)) => res1 + res2
      case (_, Left(y)) =>
        throw new Exception(s"ERROR on calling Webservice2 with id $id and message $y")
      case (None, _) => {
        throw new Exception(s"ERROR on calling Webservice2 with id $id")
      }
    }

    resCompute
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
