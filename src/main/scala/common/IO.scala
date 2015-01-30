package edu.luc.cs.comp372
package common

import java.io.{OutputStreamWriter, BufferedWriter}

/**
 * Created by bruno on 1/28/15.
 */
trait IO {

  val IO_BUF_SIZE = 8192

  implicit val stdout = new BufferedWriter(new OutputStreamWriter(System.out), IO_BUF_SIZE)

  /**
   * Print the distribution of words following the requirements:
   *  - Ascending order of lengths;
   *  - Number of words right aligned;
   *  - Length of histogram bars scaled to the max width of the
   *     screen
   */
  def print(distribution: Map[Int, Int])(implicit out: BufferedWriter): Unit = {
    if (distribution.isEmpty) return

    val maxRepetition = distribution.valuesIterator.max
    val maxRepetitionStrLength = maxRepetition.toString.size

    val maxWordSize = distribution.keysIterator.max
    val maxWordSizeStrLength = maxWordSize.toString.size

    lazy val maxColumns = getMaxColumns(maxRepetition, maxWordSize)

    for (wordSize <- 1 to maxWordSize) {
      val repetition = distribution.getOrElse(wordSize, 0)
      val bar = createBar(repetition)
      appendToOutput(wordSize, repetition, bar)
    }
    out.flush()

    def createBar(repetition: Int): String = {
      if (repetition == 0) ""
      else " " + "*" * (scale(repetition, maxColumns, maxRepetition))
    }

    def appendToOutput(wordSize: Int, repetition: Int, bar: String): Unit = {
      out.append("%" + maxWordSizeStrLength + "d " +
                 "%" + maxRepetitionStrLength + "d" +
                 "%s" format (wordSize, repetition, bar))
      out.newLine()
    }

  }

  def scale(repetition: Int, maxColumns: Int, maxRepetition: Int): Int = {
    if (maxRepetition > maxColumns)
      (maxColumns * (repetition / maxRepetition.toFloat)).toInt
    else
      repetition
  }

  def getMaxColumns(subtractFromMaxColumns: Int*): Int = {
    /*
     * Sum of the size of the two fields:
     *  <size of word> + <quantity of words with specific size>
     * Besides that, sums two blank spaces used to separate the fields
     */
    val numColsToSkip = subtractFromMaxColumns.foldLeft(2)((b, a) => b + a.toString.size)

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
