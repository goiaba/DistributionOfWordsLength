package edu.luc.cs.comp372
package common

import java.io.{BufferedWriter, StringWriter}

import org.scalatest.WordSpec

/**
 * Created by bruno on 1/29/15.
 */
class PrintSpec extends WordSpec with IO {

  def fixture() = new {
    val stringWriter = new StringWriter
    implicit val strout = new BufferedWriter(stringWriter, IO_BUF_SIZE)
  }

  "The word length Distribution printer" when {
    "given an empty list of words" should {
      "print nothing" in {
        val f = fixture()
        print(Fixtures.empty)(f.strout)
        assert(f.stringWriter.toString == "")
      }
    }

    "given a list with \'    apple apple   banana  apple\'" should {
      "print this graphic correctly" in {
        val f = fixture()
        print(Fixtures.someWords)(f.strout)
        assert(f.stringWriter.toString ==
          """|1 0
             |2 0
             |3 0
             |4 0
             |5 3 ***
             |6 1 *
             |""".stripMargin)
      }
    }

    val widthOverflow = "banana " * 150 + "apple " * 250 + "\n" + "blackberry " * 50

    "given a list with \'" + widthOverflow + "\' which overflows the terminal width" should {
      "print this graphic correctly" in {
        val f = fixture()
        print(Fixtures.widthOverflow)(f.strout)
        def scale(repetition: Int): Int = super.scale(repetition, getMaxColumns(250, 10), 250)
        assert(f.stringWriter.toString ==
          """| 1   0
             | 2   0
             | 3   0
             | 4   0
             | 5 250 """.stripMargin + "*" * scale(250) +
          """|
             | 6 150 """.stripMargin + "*" * scale(150) +
          """|
             | 7   0
             | 8   0
             | 9   0
             |10  50 """.stripMargin + "*" * scale(50) + "\n")
      }
    }

  }
}
