package com.particeep.test.basic

import com.particeep.test.basic.Refactoring.File
import org.scalatestplus.play.PlaySpec

class BasicScalaTest extends PlaySpec {

  "BasicScala" should {
    "encode params in URL" in {
      // GIVEN
      val params = Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
      // WHEN
      val output = BasicScala.encodeParamsInUrl(params)
      // THEN
      output mustBe "?sort_by=name&order_by=asc&user_id=12"
    }

    "compute email" in {
      BasicScala.isEmail("jean@particeep.com") mustBe true
      BasicScala.isEmail("jean_particeep.com") mustBe false
    }

    "compute power" in {
      BasicScala.power(i = 2, n = 3) mustBe 8
      BasicScala.power(i = 99, n = 38997) mustBe 1723793299
    }
  }

  "ComputeAverage" should {
    "compute average of the list" in {
      ComputeAverage.average(List(1, 10, 16)) mustBe 9.0
    }
  }

  "GetCategories" should {
    "return a list of unique categories" in {
      // GIVEN
      val files = List(
        File("file1", "txt"),
        File("file2", "conf"),
        File("file3", "yaml"),
        File("file4", "swagger"),
        File("application.conf", "conf"),
        File("swagger", "swagger")
      )

      // WHEN
      val categories = Refactoring.getCategoriesOpt(files)

      // THEN
      categories mustBe List("txt", "conf", "yaml", "swagger")
    }

    "return an error" in {
      // GIVEN
      val files = List(
        File("file1", "txt"),
        File("file2", "yaml")
      )
      //WHEN
      val categories = Refactoring.getCategoriesOpt(files)
      // THEN
      categories mustNot be(List("txt"))
    }
  }
}
