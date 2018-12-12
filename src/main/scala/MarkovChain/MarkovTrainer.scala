package MarkovChain

import scala.collection.mutable

object MarkovTrainer {
  val windowSize = 2

  def train(lines: Iterator[String],
            corpusWeightMap: mutable.Map[String, mutable.Map[String, Int]]): Unit = {
    lines.foreach(adjustWeight(_, corpusWeightMap))
  }

  private def adjustWeight(sentence: String,
                   corpusWeightMap: mutable.Map[String, mutable.Map[String, Int]]) = {
    val segments = segmentSentence(sentence)

    segments.foreach(segment => {
      val key = segment.head
      val weightMap = corpusWeightMap.getOrElse(key, scala.collection.mutable.Map())
      weightMap(segment.last) = weightMap.getOrElse(segment.last, 0) + 1
      corpusWeightMap(key) = weightMap
    })
  }

  private def segmentSentence(sentence: String) ={
    sentence.
      split(" ")
      .+:("")
      .:+("")
      .sliding(windowSize)
      .toList
  }
}
