package com.particeep.test.akka

import akka.actor.{ Actor, ActorSystem, Props }
import akka.stream.ActorMaterializer

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
class BasicActor() extends Actor {

  override def receive: Receive = {
    case "Hello" => println("Hello there.")
    case _ => println("What?")
  }

}


object FireActor {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */


  implicit val system = ActorSystem("mySystem")
	implicit val materializer = ActorMaterializer()
	implicit val ec = system.dispatcher

  val basicActor = system.actorOf(Props(new BasicActor),name="one BasicActor")


  def fireActor(): Unit = {
    basicActor ! "Hello"
    basicActor ! "anything"
  }

  fireActor()
}
