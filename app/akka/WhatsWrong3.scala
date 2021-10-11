package com.particeep.test.akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 *
 * The fact that onComplete is non blocking could leads to inconsistency on the internalState
 * because multiple messages could be received and the order of processing is not guaranteed
 * I can also see that the pattern matching is not exhaustive,
 * I think it could leads to dead letters
 *
 * What would you do to fix it ?
 *
 * I would add the notion of queue to have the messages ordered or put a lock on the internal state to avoid processing
 * conflicts on the internal state mutation.
 * I would also add the case _ to receive all other messages.
 *
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
    case _ => println("This kind of message is not handled.")
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
