package services
import java.util.Date

import model.{MomentumQuote, MomentumResponse}

object MomentumService {
  def endOfMonthQuotes(quotes : List[MomentumQuote]) : List[MomentumQuote] = {
    quotes
      .groupBy(quote => (quote.Symbol, quote.Date.getYear, quote.Date.getMonth))
      .map(_._2.reduceLeft((q1,q2) => if (q1.Date.after(q2.Date)) q1 else q2))
      .toList
  }

  def calculateMomentum(quotes: List[MomentumQuote]) : List[MomentumResponse] = {
    val orderedQuotes = quotes.sortBy(_.Date)(Ordering[Date].reverse)
    orderedQuotes.groupBy(_.Symbol).map(q=>{
      val groupedQuotes = q._2
      val momentumPeriods = List(1,3,6,9,12)
      Map(q._1 -> momentumPeriods
        .map(groupedQuotes.head.AdjustedClose / groupedQuotes(_).AdjustedClose)
        .sum
        .setScale(2, BigDecimal.RoundingMode.HALF_UP))
    }).flatten(m => m).map(t => MomentumResponse(t._1, t._2)).toList.sortBy(_.value).reverse
  }
}
