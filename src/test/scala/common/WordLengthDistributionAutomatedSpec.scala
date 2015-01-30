package edu.luc.cs.comp372
package common

import org.scalatest.WordSpec

/**
 * Created by bruno on 1/29/15.
 */
trait WordLengthDistributionAutomatedSpec extends WordSpec with WordLengthDistribution {

  val maxNumberOfWordsTest = 50
  val numberOfTestCases = 5

  "The word length Distribution" when {
    for (t <- 1 to numberOfTestCases)
      testDriver((math.random * maxNumberOfWordsTest).toInt)
  }

  def testDriver(QtdWords: Int) = {
    val testHelper: WordLengthDistributionHelper = new WordLengthDistributionHelper(QtdWords)

    "given a list with \'" + testHelper.stringRepresentationOfInput + "\'" should {
      "contain a " + testHelper.stringRepresentationOfDistributionMap in {
        assert(compute(testHelper.linesIterator) == testHelper.distributionMap)
      }
    }
  }
}
