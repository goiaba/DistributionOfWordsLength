package immutable

import common.WordLengthDistribution


/**
  * Created by bruno on 1/28/15.
 */
object Main extends common.Main with Immutable

trait Immutable extends WordLengthDistribution {
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
   * @param words a list of words, separated by blank spaces, to be
   *               included in the distribution
   * @return a map containing the word length as key and the number
   *          of times words with that length appears as value
   */
  def compute(words: String) =
    words.trim.split("\\s+").filter(_.nonEmpty).groupBy(_.length).mapValues(_.size)
}