package MarkovChain

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Generator {
  val seed = new Random

  def getNextSentence(originWords: List[String],
                      corpusWeightMap: mutable.Map[String, mutable.Map[String, Int]]) = {
    val start = originWords(seed.nextInt(originWords.size))
    val sentence:ArrayBuffer[String] = ArrayBuffer()
    sentence.append(start)

    while (sentence.last != ""){
      sentence.append(getNextWord(sentence.last, corpusWeightMap))
    }

    sentence.view(1, sentence.size - 1).mkString(" ").capitalize
  }

  def getNextWord(currentWord: String,
                  corpusWeightMap: mutable.Map[String, mutable.Map[String, Int]]) = {
    val wordRange = corpusWeightMap.getOrElse(currentWord, List())

    seed.shuffle(wordRange.flatMap(pair => List.fill(pair._2)(pair._1))).head
  }
}
