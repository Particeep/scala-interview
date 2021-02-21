package com.particeep.test

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 * What would you do to fix it ?
 * Do not mind about the not implemented code
 */
 
 // we might have a race condition, since the receive function and the Future's onComplete can be running in the same time 
 // the solution to this problem would be sending a message to the actor in the case of Success
 // when the actor receive this message he can operate the mutation there, this would avoid the race conditon.

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
