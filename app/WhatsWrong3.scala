package whats_wrong

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext

/*
Do you see anything that could lead to potential problems ?
What would you do to fix it ?
Do not mind about the not implemented code
*/

/*

Solution
--------

here we use scala.concurrent.ExecutionContext.Implicits.global, it could cause a confusion 
between business logic and code execution logic while calling a future request.
To fix it i will declare execution context as implicit like bellow

Another problem is that we have a stateless actor that not implemented correctly, to fix it i will use akka FSM, here i dont know all 
actor states so i cant write an example. but all needed infos are in the link bellow 
https://doc.akka.io/docs/akka/2.5.4/scala/fsm.html

*/

class WhatsWrong3(implicit ec: ExecutionContext) extends Actor {

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
