package whats_wrong

import akka.actor.{ Actor, ActorSystem, Props }

/*
When receiving a message that says "Hello", BasicActor must print "Hello there."
It must print "What?" when receiving any other message
*/

class BasicActor extends Actor {
	def receive  = {
		case "Hello" => println("Hello there.")
		case _ => println("What?")
	}
}

object FireActor {

/*  Create an instance of BasicActor
  Make it print "Hello there." and "What?"*/
  def fireActor(): Unit = {
       val system = ActorSystem("whats_wrong")
       val basic_actor = system.actorOf(Props[BasicActor], name = "basic_actor")
       basic_actor ! "Hello"
       basic_actor ! "foo"
  }
}
