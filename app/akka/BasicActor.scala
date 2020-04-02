package com.particeep.test.akka

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */

class BasicActor extends Actor {
  def receive: Receive = {
    case "Hello" => println("Hello there.")
    case _       => println("What?")
  }
}

object FireActor {

  /**
    * Create an instance of BasicActor
    *
    * Make it print "Hello there." and "What?"
    */
  def fireActor(): Unit = {
    val system = ActorSystem("Actor-FireActor")
    val basic_actor = system.actorOf(Props[BasicActor], name = "basic-actor")

    basic_actor ! "Hello"
    basic_actor ! "Other"
  }
}
