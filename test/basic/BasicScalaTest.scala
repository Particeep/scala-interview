package com.particeep.test.basic

import org.scalatest.wordspec.AnyWordSpec

class BasicScalaTest extends  AnyWordSpec {
  "[basic]BasicScala - 1st exercice" when {
    "empty map value" should {
      val empty: String = BasicScala.encodeParamsInUrl(Map())
      "return string empty" in {
        assert(empty == "")
      }
    }
    "correct value" should {
      "return correct String formatted" in {
        val input: Map[String, String] = Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
        val a: String                  = BasicScala.encodeParamsInUrl(input)
        val expected: String           = "?sort_by=name&order_by=asc&user_id=12"
        assert(a === expected)
      }
    }
  }
  "[basic]BasicScala - 2nd exercice" when {
    "thiaw.koon@gmail.com" should {
      val email: String = "thiaw.koon@gmail.com"
      val result        = BasicScala.isEmail(email)
      "return true" in {
        assert(result === true)
      }
    }
    "thiaw.koon_gmail.com" should {
      val email: String = "thiaw.koon_gmail.com"
      val result        = BasicScala.isEmail(email)
      "return false" in {
        assert(result === false)
      }
    }
    "thiaw.koon@gmail" should {
      val email: String = "thiaw.koon@gmail"
      val result        = BasicScala.isEmail(email)
      "return false" in {
        assert(result === false)
      }
    }
    "thiaw.koon@gmail_.com" should {
      val email: String = "thiaw.koon@gmail_.com"
      val result        = BasicScala.isEmail(email)
      "return false" in {
        assert(result === false)
      }
    }
    "thiaw.koon\n@gmail_.com" should {
      val email: String = "thiaw.koon\n@gmail_.com"
      val result        = BasicScala.isEmail(email)
      "return false" in {
        assert(result === false)
      }
    }
    "thiaw.koon@gmail_a.com" should {
      val email: String = "thiaw.koon@gmail_a.com"
      val result        = BasicScala.isEmail(email)
      "return false" in {
        assert(result === false)
      }
    }
    "thiaw.koon@gmail-a.com" should {
      val email  = "thiaw.koon@gmail-a.com"
      val result = BasicScala.isEmail(email)
      "return false" in {
        assert(result === true)
      }
    }
  }
  "[basic]BasicScala - 3rd exercice" when {
    "power(2,3)" should {
      val result   = BasicScala.power(2, 3)
      val expected = 8
      "return" in {
        assert(result === expected)
      }
    }
    "power(99,38997)" should {
      val result   = BasicScala.power(99, 38997)
      val expected = 1723793299
      "return" in {
        assert(result === expected)
      }
    }
    "power(0,38997)" should {
      val result   = BasicScala.power(0, 38997)
      val expected = 0
      "return" in {
        assert(result === expected)
      }
    }
    "power(1,38997)" should {
      val result   = BasicScala.power(1, 38997)
      val expected = 1
      "return" in {
        assert(result === expected)
      }
    }

  }
}
