package async

import com.particeep.test.async.{AsyncBasic, ValueNotFoundException}
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.PlaySpec

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class AsyncBasic extends PlaySpec with ScalaFutures {

  "AsyncScala" should {
    "compute the sum of the 2 webservices call" in {
      // GIVEN
      val id = "1"
      // WHEN
      val result = Await.result(AsyncBasic.compute(id), 1 second)
      // THEN
      result mustBe 1099
    }

    "throw ValueNotFoundException when id not present" in {
      // GIVEN
      val id = "2"
      // WHEN
      val result = AsyncBasic.compute(id)
      // THEN
      result.failed.futureValue(timeout(1 second)) mustBe a[ValueNotFoundException]
    }
  }

}
