package akka

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import com.particeep.test.akka.FireActor
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class BasicActorSpec
  extends TestKit(ActorSystem("BasicActorSystem"))
    with ImplicitSender
  with AnyWordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override protected def afterAll(): Unit = TestKit.shutdownActorSystem(system)

  "A Fire actor" must {
    "print 'hello there' when receiving 'Hello'" in {
      FireActor.fireActor()
    }
  }
}
