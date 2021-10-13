package com.particeep.test.basic

import com.particeep.test.basic.Refactoring.File
import org.scalatest.wordspec.AnyWordSpec

class RefactoringTest extends AnyWordSpec{
  "[basic]Refactoring]" when {
    "given a list of file" should {
      val listFile: List[File] = List(
        File("L'exorciste", "Horror"),
        File("Fast & furious", "Action"),
        File("Demolition Man", "Action"),
        File("Matrix", "Action"),
        File("Yves St Laurent", "Biopic"),
        File("Steve Jobs", "Biopic"))
      "return expected list" in {

        val lstCategorie = Refactoring.getCategories(listFile)
        val expected = List("Horror", "Action", "Biopic")
        println(lstCategorie)
        assert(lstCategorie.equals(expected))
      }
    }
    "given an empty list" should {
      val listFile: List[File] = List()
      "return empty list" in {
        val lstCategorie = Refactoring.getCategories(listFile)
        val expected = List()
        assert(lstCategorie.equals(expected))
      }
    }
  }
}
