package com.particeep.test.akka

import akka.actor.{ Actor, ActorSystem, Props }

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
class BasicActor  extends Actor {
  def receive: Receive = {
    case "Hello" => print("Hello there.")
    case _ => print("What?")
  }

  def print(msg:String) =
    println(msg)
}

object FireActor {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def fireActor(): Unit = {
        val system = ActorSystem("ActorSystem")
        val basicActor = system.actorOf(Props[BasicActor], name = "basicActor")
        basicActor ! "Hello"
        basicActor ! "How are you?"
  }
}
