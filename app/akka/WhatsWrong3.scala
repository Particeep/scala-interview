package com.particeep.test.akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 *  => The main problem to me is the use of var to maintain an internal state.
 *  Accessing an internal state variable introduces access concurrency issues to this variable.
 *  For this specific code, as far as the internal state is not exposed externally, it remains ok,
 *  but it could potentially be externally modified, for example by passing this variable to another actor.
 *
 * What would you do to fix it ?
 *  => I would use the become/unbecome pattern to mutate internal state
 * Do not mind about the not implemented code
 */
class WhatsWrong3 extends Actor {

  var internalState = "internal state"

  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
