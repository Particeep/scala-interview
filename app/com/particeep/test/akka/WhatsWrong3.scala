package com.particeep.test.akka

import akka.actor.{ Actor, ActorRef }

import scala.concurrent.{ ExecutionContext, Future }
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
object Messages {
  case object GetResultFromServer
}

class QueryAsyncServerActor extends Actor {
  implicit val ec: ExecutionContext      = context.dispatcher
  def queryAsyncServer(): Future[String] = ???

  override def receive: Receive = {
    case Messages.GetResultFromServer => (queryAsyncServer(): Future[String]).pipeTo(sender())
  }
}

/**
 * Do you see anything that could lead to potential problems ?
 * What would you do to fix it ?
 * Do not mind about the not implemented code
 */
class WhatsWrong3(serverRef: ActorRef) extends Actor {

  var internalState                 = "internal state"
  implicit val ec: ExecutionContext = context.dispatcher
  implicit val timeout              = Timeout(5.seconds)

  def receive: Receive = {
    case "a query" => {
      (serverRef ? Messages.GetResultFromServer).collect {
        case msg: String => handleResponse(msg)
      }.pipeTo(sender())
    }
  }

  def handleResponse(r: String) = ??? // mutate internal state

}
