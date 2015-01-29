package edu.luc.cs.comp372
package common

/**
 * Created by bruno on 1/28/15.
 */
trait IO {
  /**
   *
   * @return a string containing all the input lines concatenated
   *          and separated by a blank space
   */
  def input = {
    val lines = scala.io.Source.stdin.getLines
    lines.map(_.replaceAll("\\s+", " ").trim).filter(_.nonEmpty)
  }

  /**
   * Print the distribution of words following the requirements:
   *  - Ascending order of lengths;
   *  - Number of words right aligned;
   *  - Length of histogram bars scaled to the max width of the
   *     screen
   */
  def print(distribution: Map[Int, Int]) = {
    if (!distribution.isEmpty) {
      val maxWordRepetition = distribution.valuesIterator.max
      val biggestWord = distribution.keysIterator.max
      lazy val terminalWidth = getMaxScreenWidth(maxWordRepetition, biggestWord)

      for (lengthOfWord <- 1 to biggestWord) {
        val quantityOfWords = distribution.getOrElse(lengthOfWord, 0)
        val barSize = "*".*(scale(quantityOfWords))
        println("%" + biggestWord.toString.size + "d " +
          "%" + maxWordRepetition.toString.size + "d " +
          "%s" format (lengthOfWord, quantityOfWords, barSize))
      }

      def scale(quantityOfWords: Int) = {
        if (maxWordRepetition > terminalWidth)
          (terminalWidth * (quantityOfWords / maxWordRepetition.toFloat)).toInt
        else
          quantityOfWords
      }
    }

    def getMaxScreenWidth(subtractFromWidth: Int*): Int = {
      /*
       * Sum of the size of the two fields:
       *  <size of word> + <quantity of words with specific size>
       * Besides that, sums two blank spaces used to separate the fields
       */
      val numColsToSkip = subtractFromWidth.foldLeft(2)((b, a) => b + a.toString.size)

      /*
       * Get the terminal width from $COLUMNS environment variable if it
       *  is set, otherwise uses the standard value (80 columns).
       */
      val width = scala.util.Properties.envOrElse("COLUMNS", "80").toInt

      /*
       * Return the number of columns minus the number of the columns already
       *  used to print the size of the word and the quantity of words with
       *  specific size.
       */
      width - numColsToSkip
    }
  }
}
