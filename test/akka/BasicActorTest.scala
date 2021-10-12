package com.particeep.test.akka

import akka.actor.{ ActorSystem, Props }
import akka.testkit.{ ImplicitSender, TestKit, TestProbe }
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class BasicActorTest
  extends TestKit(ActorSystem("MySpec"))
    with ImplicitSender
    with AnyWordSpecLike
    with Matchers
    with BeforeAndAfterAll {
  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
  "BasicActor" when {
    val probe      = TestProbe()
    val basicActor = system.actorOf(Props(new BasicActor() {
      override def print(msg: String) =
        probe.ref ! msg
    }))
    "Hello message sent" should {
      basicActor ! "Hello"
      "println 'Hello there.' message" in {
        probe.expectMsg("Hello there.")
      }
    }
    "A message different of 'Hello'" should {
      basicActor ! "Hi"
      "println 'What?' message" in {
        probe.expectMsg("What?")
      }
    }
  }
}
