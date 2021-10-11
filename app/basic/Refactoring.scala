package com.particeep.test.basic

import scala.collection.mutable.ListBuffer

/**
 * What is the complexity of the function ?
 * This operation has a complexity of O(n) depending on collection size, the bigger the list is, the longer it takes to be executed"
 *
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def getCategories(files: List[File]): ListBuffer[String] = {
    val categories: ListBuffer[String] = ListBuffer()

    if(files != null) {
      for(file <- files) {
        if(file.category != null && !categories.contains(file.category)) {
          categories += file.category
        }
      }
    }

    categories
  }
}
