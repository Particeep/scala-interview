package com.particeep.test.basic

/**
 * Compute the average of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAverage {

  def average(list: List[Double]) = {
    list.sum / list.length
  }
}
