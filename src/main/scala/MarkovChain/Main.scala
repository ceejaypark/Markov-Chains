package MarkovChain

import scala.collection.mutable
import scala.io.{Codec, Source}

object Main extends App {
  val corpusWeightMap:mutable.Map[String, mutable.Map[String, Int]] = new mutable.HashMap()
  val lines = readFromFile;

  MarkovTrainer.train(lines, corpusWeightMap)

  val originWords = Sanitizer.getSentenceStarters(corpusWeightMap)

  (0 until 10).
    map(_ => println(Generator.getNextSentence(originWords, corpusWeightMap)))

  def readFromFile() = {
    Source.fromFile("src/resources/TestDoc.txt")(Codec("ISO-8859-1"))
      .getLines()
      .flatMap(Sanitizer.cleanString(_)
        .split("(\\.|\\?)"))
      .map(_.trim)
  }
}
