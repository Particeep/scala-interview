package com.particeep.test.akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?

  FOR ME: The main problem reside in the fact that async associated with future on queryAsyncServer does not garanty order of execution with the onComplete...
  Then as the internal state is changed on success, there will be no notion of order on modifications of internal state. The internal state could be modified in unpredictable ways (hard to track or predict due to non-garanty from onComplete relative to threads)

 * What would you do to fix it ?

  FOR ME: either
    - Use Await ready to get sequential ordering on modification of the internal state. If compatible with the actor in the global context of the software
    - Switch to message/event queue to get strong ordering if an event oriented can be used.

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
