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

  /**
  * You actor could be close before the onComplete should be execute.
   * So the handleResponse could not be updated every time so your internalState is not concistant.
   *
   * Actually i don't like to use mutable state and global ExecutionContext.
   *
   * You can use requestF.pipeTo() to notify the sender that your request then the handle is done
   * case "a query" => {
   * val requestF: Future[_] = queryAsyncServer().map(handleResponse)
   *       requestF.pipeTo(sender())
   * }
   * }
   *
   */
}
