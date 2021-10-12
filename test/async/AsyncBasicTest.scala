package com.particeep.test.async

import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class AsyncBasicTest  extends AnyWordSpec{
  "[Async]AsyncBasic" when {
    "compute(1)" should {
      val result = AsyncBasic.compute("1")
      val resultasync = Await.result(result,Duration.Inf)
      "return 1099" in {
        val expected = 1099
        assert(resultasync===expected)
      }
    }
    "compute(2)" should {
      val result: Future[Int] = AsyncBasic.compute("2")
      "return exception" in {
        assertThrows[Exception](Await.result(result,Duration.Inf))
      }
    }
    "compute(3)" should {
      val result: Future[Int] = AsyncBasic.compute("3")
      "return exception" in {
        assertThrows[Exception](Await.result(result,Duration.Inf))
      }
    }
    "compute(5)" should {
      val result: Future[Int] = AsyncBasic.compute("5")
      "return exception" in {
        assertThrows[Exception](Await.result(result,Duration.Inf))
      }
    }
    "compute(9)" should {
      val result: Future[Int] = AsyncBasic.compute("9")
      "return exception" in {
        assertThrows[Exception](Await.result(result,Duration.Inf))
      }
    }
    "compute(10)" should {
      val result = AsyncBasic.compute("10")
      val resultasync = Await.result(result,Duration.Inf)
      "return Int.MaxValue" in {
        val expected = Int.MaxValue - 1985
        assert(Math.abs(resultasync)===expected)
      }
    }
  }
}
