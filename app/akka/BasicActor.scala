package com.particeep.test.akka

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */

object BasicActor {
  def props = Props(new BasicActor())
}

class BasicActor() extends Actor with ActorLogging {
  override def receive: Receive = {
    case "Hello" => log.info("Hello there")
    case _ => log.info("What?")
  }
}

object FireActor {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def fireActor(): Unit = {
    val system = ActorSystem("ActorSystem")
    val basic_actor = system.actorOf(Props[BasicActor], name = "basic-actor")

    basic_actor ! "Hello"
    basic_actor ! "World"
  }
}