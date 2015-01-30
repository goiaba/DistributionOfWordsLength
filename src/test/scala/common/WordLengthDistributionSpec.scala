package edu.luc.cs.comp372
package common

import org.scalatest.WordSpec

/**
 * Created by bruno on 1/29/15.
 */
trait WordLengthDistributionSpec extends WordSpec with WordLengthDistribution {

  "The word length Distribution" when {
    "given an empty list of words" should {
      "contain a Map()" in {
        assert(compute(Iterator.empty) == Fixtures.empty)
      }
    }

    "given a list with \'    apple apple   banana  apple\'" should {
      "contain a Map(5 -> 3, 6 -> 1)" in {
        val words = Iterator("    apple apple   banana  apple")
        assert(compute(words) == Fixtures.someWords)
      }
    }

    "given a list with \'apple apple   banana  apple     \'" should {
      "contain a Map(5 -> 3, 6 -> 1)" in {
        val words = Iterator("apple apple   banana  apple     ")
        assert(compute(words) == Fixtures.someWords)
      }
    }

    "given a list with \'          apple      apple     \n   banana  apple     \'" should {
      "contain a Map(5 -> 3, 6 -> 1)" in {
        val words = Iterator("          apple      apple     ", "   banana  apple     ")
        assert(compute(words) == Fixtures.someWords)
      }
    }
  }

}
