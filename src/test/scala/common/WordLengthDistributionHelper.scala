package edu.luc.cs.comp372
package common

import scala.collection.mutable.{HashMap, ListBuffer}
/**
 * Created by bruno on 1/29/15.
 */
class WordLengthDistributionHelper(quantityOfWords: Int) {
  private val dictionary = Array("\n", "soccer", "goalkeeper", "java",
    "scala", "development", "tests",
    "range", "dynamic", "requirements",
    "nonfunctional", "functional", "project",
    "learning", "transition", "understanding",
    "imperative", "higher-order", "test-driven",
    "tdd", "order", "reach", "sbt", "framework")
  private val map = new HashMap[Int, Int]()
  private var lines = new String()

  create

  def distributionMap = collection.immutable.HashMap() ++ map
  def stringRepresentationOfDistributionMap = map.mkString("Map(", ", ", ")")
  def stringRepresentationOfInput = new String(lines)
  def linesIterator = lines.split("\\n").toIterator

  private def create: Unit = {
    for (i <- 1 to quantityOfWords) {
      val word = getRandomTypedWord
      lines += getRandomBlankSpaces + word + getRandomBlankSpaces
      if (!"\n".equals(word)) map.update(word.length, map.getOrElse(word.length, 0) + 1)
    }
  }
  private def getRandomTypedWord: String = dictionary((math.random * dictionary.length).toInt)
  private def getRandomBlankSpaces: String = " " * ((math.random * 10).toInt + 1)
}
