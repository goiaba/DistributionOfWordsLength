package edu.luc.cs.comp372
package common

/**
 * Created by bruno on 1/28/15.
 */
trait WordLengthDistribution {
  /**
   * Iterate over every word contained in the words parameter and
   *  update the map of distributions
   *
   * Includes a new entry with the value 1 if the map does not
   *  contain a key corresponding to the length of the word being
   *  processed. Otherwise, sum one to the existing key value. It
   *  is case sensitive so words are considered to be equal only
   *  if they are typed exactly in the same way
   *
   * @param words an iterator of words to be included in the
   *               distribution
   * @return a map containing the word length as key and the number
   *          of times words with that length appears as value
   */
  def compute(words: Iterator[String]): Map[Int, Int]
}
