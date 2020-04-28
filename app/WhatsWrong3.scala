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
      /**
      * rerequestF instenciate in future will block
      * or onComplete () to capture the result of a Future. Successfully and unsuccessfully 
      */
      val requestF = queryAsyncServer()

      //val requestF: Future[String] = queryAsyncServer()

      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) = _ // mutate internal state

  def queryAsyncServer(): Future[String] = "https://test.com?"
}
