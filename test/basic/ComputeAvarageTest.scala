package com.particeep.test.basic

import org.scalatest.wordspec.AnyWordSpec

class ComputeAvarageTest extends AnyWordSpec {
  "[basic]ComputeAvarage" when {
    "average of [1,10,16]" should {
      val list: List[Double] = List(1, 10, 16)
      "return 9" in {
        val expected = 9
        val result   = ComputeAvarage.average(list)
        assert(result === expected)
      }
    }
    "average of [1]" should {
      val list: List[Double] = List(1)
      "return 9" in {
        val expected = 1
        val result   = ComputeAvarage.average(list)
        assert(result === expected)
      }
    }
    "average of [1,2,3,4,5,...,100]" should {
      val list: List[Double] = (1 to 100).toList.map(a=>a.toLong)
      "return 50.5" in {
        val expected = 50.50
        val result   = ComputeAvarage.average(list)
        assert(result === expected)
      }
    }
    "average of []" should {
      val list: List[Double] = List()
      "return 0" in {
        val expected = 0
        val result   = ComputeAvarage.average(list)
        assert(result === expected)
      }
    }
  }
}
