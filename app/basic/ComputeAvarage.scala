package com.particeep.test.basic

/**
 * Compute the avarage of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAvarage {

  def average(l: List[Double]): Double = l.size match {
          case 0 => 0L
          case _ => l.sum / l.size
        }
}
