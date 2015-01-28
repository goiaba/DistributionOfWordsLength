package common

/**
 * Created by bruno on 1/28/15.
 */
trait Main extends App with IO with WordLengthDistribution {
  override def main (args: Array[String]): Unit = {
    println("\nType in the words separated by spaces. Press <Ctrl+d> or <Ctrl+z> to stop.")
    val distribution = compute(input)
    print(distribution)
  }
}
