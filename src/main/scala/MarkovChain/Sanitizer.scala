package MarkovChain

import scala.collection.mutable

object Sanitizer {
  def cleanString(line: String) = {
    line.stripLineEnd
      .toLowerCase
      .filterNot(sentence => "\\.-,\";:&" contains sentence)
  }

  def getSentenceStarters(corpusWeightMap: mutable.Map[String, mutable.Map[String, Int]]) = {
    corpusWeightMap("")
      .keys
      .toList
  }
}
