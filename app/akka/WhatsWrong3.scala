package com.particeep.test.akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 **** First, with onComplete, the Future is non-blocking, so when Future
 *  is successful, it will change internalState. The problem occurs if
 *  there are several messages in success, there is no garanty that
 *  internalState will have a consistent value :
 *  no orderly treatment = unpredictable value
 **** Secondly, the Akka Actor receive message has to be exhaustive.
 * Otherwise it will publish an akka.actor.UnhandledMessage
 *
 * What would you do to fix it ?
 **** For the first problem, I'll use a "Await.ready" that will block
 *  Future until it's complete
 **** Then, I'll add a default pattern match "case _ => XXX" to treat
 *  all message. The result "XXX" depends of the context of the Application
 *  (thrown an exception, ignore message, log a message, etc.)
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
