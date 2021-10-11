package com.particeep.test.basic

import com.particeep.test.akka.FireActor
import com.particeep.test.async.AsyncBasic
import com.particeep.test.async.WhatsWrong2.{getCEOAndEnterprise, getCEOAndEnterpriseOCO}
import com.particeep.test.basic.Refactoring.File
import play.api.i18n.Lang
import org.scalatestplus.play.PlaySpec

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, FiniteDuration}

class BasicScalaTest extends PlaySpec {

  "BasicScala" should {
    "compute email" in {
      BasicScala.isEmail("jean@particeep.com") mustBe true
      BasicScala.isEmail("jean_particeep.com") mustBe false
    }
  }

  "encodeParamsInUrl" should {
    "returns en empty string if the map is empty" in {
      BasicScala.encodeParamsInUrl(Map()) mustBe("")
      BasicScala.encodeParamsInUrl(Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")) mustBe("?sort_by=name&order_by=asc&user_id=12")
    }
  }

  "power" should {
    "return ?? if (i = 2, n = -1" in {
      BasicScala.power(2,0) mustBe(2)
    }
    "return 8 if i=2 and n=3" in {
      BasicScala.power(2,3) mustBe(8)
    }
    "1723793299" in {
      BasicScala.power(99,38997) mustBe(1723793299)
    }
    "return ?? if (i = 2, n = 1" in {
      BasicScala.power(2,1) mustBe(2)
    }
    "return ?? if (i = 2, n = 0" in {
      BasicScala.power(2,0) mustBe(1)
    }
  }

  "average" should {
    "return 9 if list equals  [1, 10, 16] " in {
      val list: List[Double] = List(1, 10, 16)
      ComputeAverage.average(list) mustBe(9)
    }
  }

  "getCategories" should {
    "return a list of categories" in {
      val list: List[File] = List(
        File("toto", "cateodrie1"),
        File("toto", "cateodrie1"),
        File("toto", "cateodrie1"),
        File("toto", "cateodrie2"),
        File("toto", "cateodrie2"),
        File("toto", "cateodrie2"),
        File("toto", "cateodrie2"),
        File("toto", "cateodrie3"),
        File("toto", "cateodrie14")
      )
      Refactoring.getCategories(list) mustBe(List("cateodrie1", "cateodrie2", "cateodrie3", "cateodrie14"))
    }
  }

  "compute" should {
    "return the sum of the call of the two webservice with 1" in {
      AsyncBasic.compute("1") mustBe(1099)
    }
    "return the sum of the call of the two webservice with 2" in {
      AsyncBasic.compute("2") mustBe(1099)
    }
    "return the sum of the call of the two webservice with 5" in {
      AsyncBasic.compute("5") mustBe(1099)
    }
    "return the sum of the call of the two webservice with 10" in {
      AsyncBasic.compute("10") mustBe(-2147481662)
    }
  }

  "ceoenterprise" should {
    "return getCEOAndEnterpriseOCO" in {
      val t = getCEOAndEnterpriseOCO(Some("1"))
      val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
      val resCompute = Await.result(t, maxWaitTime) match {
        case Some(c) => c.ceo_id mustBe("1")
        case None => throw new Exception("PAS")
      }
    }

    "return getCEOAndEnterprise" in {
      val t = getCEOAndEnterprise(Some("1"))
      val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
      val resCompute = Await.result(t, maxWaitTime) match {
        case (Some(ceo), enterpriseList) => {
          ceo.id mustBe("1")
          enterpriseList.map(x=> x.ceo_id mustBe("1"))
        }
        case _ => throw new Exception("Unable to find CEO in enterprise List with ceo_id = 1")
      }
    }

    "return getCEOAndEnterprise with ceo_id=None" in {
      val t = getCEOAndEnterprise(None)
      val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
      val resCompute = Await.result(t, maxWaitTime) match {
        case (Some(ceo), enterpriseList) => {
          ceo.id mustBe("1")
          enterpriseList.map(x=> x.ceo_id mustBe("1"))
        }
        case _ => throw new Exception("Unable to find CEO in enterprise List with ceo_id = 1")
      }
    }
  }

  "fireActor" should {
    "fire the actor" in {
      FireActor.fireActor()
    }
  }

}
