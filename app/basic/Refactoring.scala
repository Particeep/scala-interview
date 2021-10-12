package com.particeep.test.basic

/**
 * What is the complexity of the function ?
 *  The complexity of the function is O(n), where n = files.size
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def getCategories(files: List[File]): List[String] = files.map(_.category).distinct
}
