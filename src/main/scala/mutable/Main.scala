package edu.luc.cs.comp372
package mutable

import common.WordLengthDistribution

/**
  * Created by bruno on 1/19/15.
 */

object Main extends common.Main with Mutable

trait Mutable extends WordLengthDistribution {
  import scala.collection.mutable.{HashMap => mutableHashMap}
  import scala.collection.immutable.{HashMap => immutableHashMap}
  /**
   * Iterate over every word contained in the words iterator and
   *  update the map of distributions
   *
   * Add a new entry with the value 1 if the map does not
   *  contain a key corresponding to the length of the word being
   *  processed. Otherwise, sum one to the existing key value. It
   *  is case sensitive so words are considered to be equal only
   *  if they are typed exactly in the same way
   *
   * @param linesOfWords an iterator of words to be included in the
   *               distribution
   * @return a map containing the word length as key and the number
   *          of times words with that length appears as value
   */
  override def compute(linesOfWords: Iterator[String]) = {
    val distributionMap = new mutableHashMap[Int, Int]
    val words = linesOfWords.map(_.trim).filter(_.nonEmpty)
      .toSeq.flatMap(_.split("\\s+"))
    for (word <- words) {
      val currentNrOfRepetition = distributionMap getOrElse(word.length, 0)
      distributionMap update(word.length, currentNrOfRepetition + 1)
    }
    immutableHashMap() ++ distributionMap
  }
}
