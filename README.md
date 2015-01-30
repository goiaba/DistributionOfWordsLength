- Both extra credit tasks were attempted (right align and scale)

#Usage

To run the tests:

    $ sbt test

To run the tests with scoverage:

    $ sbtclean coverage test

To run the main methods on a Unix-based system:

    $ sbt 'runMain edu.luc.cs.comp372.immutable.Main' 
    $ sbt 'runMain edu.luc.cs.comp372.mutable.Main' 

To run the main methods on a Unix-based system redirecting input from file:

    $ sbt 'runMain edu.luc.cs.comp372.immutable.Main' < myInputFile.txt
    $ sbt 'runMain edu.luc.cs.comp372.mutable.Main' < myInputFile.txt
