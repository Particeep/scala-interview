package com.particeep.test

import java.util.concurrent.TimeUnit

import akka.actor.Actor
import akka.pattern.pipe

import scala.concurrent.{Await, Future}
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
  * Do you see anything that could lead to potential problems ?
  * Yes - The actor is using a Future inside his receive method and it is not waiting for it to complete before returning.
  * Also in this async callback 'handleResponse' mutates the internal state which exposes us to potential race conditions.
  * Actors are useful to design async systems but an important assumption is that each actor is going to process messages
  * synchronously so the code below is somehow breaking this rule by introducing an async callback on a future.
  *
  * What would you do to fix it ?
  * See 'receiveWait' and 'receivePipe' methods.
  *
  * Do not mind about the not implemented code
  */
class WhatsWrong3 extends Actor {

  // internal states are usually better kept private, a getter could be provided if the outside world needs to access its value
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

  /**
    * The easiest way to fix it would be to wait for the future to complete by using Await.ready for example
    */
  def receiveWait: Receive = {
    case "a query" =>
      val requestF: Future[String] = queryAsyncServer()
      Await.ready(requestF, Duration.apply(10, TimeUnit.SECONDS)).value.get match {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
  }

  /**
    * We could also use the 'pipeTo' method if we wanted to keep the async behaviour and let another actor
    * (e.g. the sender) deal with the result of the future using the pipeTo pattern explained in the Akka documentation
    * https://doc.akka.io/docs/akka/current/futures.html
    */
  def receivePipe: Receive = {
    case "a query" =>
      val requestF: Future[String] = queryAsyncServer()
      requestF.pipeTo(sender())
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
