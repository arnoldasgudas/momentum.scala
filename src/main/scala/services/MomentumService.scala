package services

import model.MomentumQuote

class MomentumService {
  def endOfMonthQuotes(quotes : List[MomentumQuote]) : List[MomentumQuote] = {
    return quotes
      .groupBy(quote => (quote.Symbol, quote.Date.getYear, quote.Date.getMonth))
      .map(_._2.reduceLeft((q1,q2) => if (q1.Date.after(q2.Date)) q1 else q2))
      .toList
  }
}
