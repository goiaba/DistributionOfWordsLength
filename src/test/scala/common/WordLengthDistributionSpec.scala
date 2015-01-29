package edu.luc.cs.comp372
package common

import org.scalatest.WordSpec

/**
 * Created by bruno on 1/29/15.
 */
trait WordLengthDistributionSpec extends WordSpec with WordLengthDistribution {

  "The word length Distribution" when {
    "given an empty list of words" should "contain a Map()" in {
      assert(compute(Iterator.empty) == Fixtures.empty)
    }
  }

}
