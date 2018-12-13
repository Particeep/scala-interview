package whats_wrong

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/*
Do you see anything that could lead to potential problems ?
What would you do to fix it ?
Do not mind about the not implemented code
*/

/*
 the onComplete callback function of 'Future' executes when the 
response is ready, and not when the actor 'WhatsWrong3' ends its 
processing and is ready to process another request.
 
this can cause problem when the actor receives several requests 
and views that the actor to a state, that can cause an inconsistency 
in estas, if as it is "internasal" (the state of the actor) will be 
shared by the different requests and we lose the interest provided by "actor"
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
