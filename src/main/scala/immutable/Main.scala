package edu.luc.cs.comp372
package immutable

import common.WordLengthDistribution


/**
  * Created by bruno on 1/28/15.
 */
object Main extends common.Main with Immutable

trait Immutable extends WordLengthDistribution {
  /**
   * Iterate over every word contained in the words Iterator and
   *  creates the map of distributions
   *
   * @param words an iterator of words to be included in the
   *               distribution
   * @return a map containing the word length as key and the number
   *          of times words with that length appears as value
   */
  def compute(words: Iterator[String]) = {
    val wArray = words.toSeq.flatMap(_.split("\\s"))
    wArray.groupBy(_.length).mapValues(_.size)
  }
}