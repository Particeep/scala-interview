package com.particeep.test.akka

import akka.actor.{ Actor, ActorSystem, Props }
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
class BasicActor extends Actor {
  private val HELLO_MESSAGE       = "Hello"
  private val HELLO_THERE_MESSAGE = "Hello there."
  private val WHAT_MESSAGE        = "What?"
  private def respond: Receive    = {
    case HELLO_MESSAGE => print(HELLO_THERE_MESSAGE)
    case _             => print(WHAT_MESSAGE)
  }

  override def receive: Receive = respond
}

object FireActor {

  implicit val timeout = Timeout(3.seconds)
  import ExecutionContext.Implicits.global

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def fireActor(): Unit = {
    val system      = ActorSystem("ActorSystem")
    val basic_actor = system.actorOf(Props[BasicActor](), name = "basic_actor")
    basic_actor ! "Hello"
    basic_actor ! "BlaBla"
  }
}
