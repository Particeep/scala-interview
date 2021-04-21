package com.particeep.test.basic

/**
 * What is the complexity of the function ? O(n²)
 *
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def getCategories(files: List[File]): List[String] = {
    val categories: List[String] = List()

    if(files != null) {
      for(file <- files) {
        if(file.category != null && !categories.contains(file.category)) {
          categories :+ file.category
        }
      }
    }

    categories
  }

  def getCategoriesOpt(files: List[File]): List[String] =
    files.map(_.category).distinct

}
