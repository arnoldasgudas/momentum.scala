package services

import model.YahooFinanceSymbol.YahooFinanceSymbol
import model.{YahooFinanceHistoricalDataResponseWrapper, YahooFinanceQuote, YahooFinanceSymbol}
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import org.json4s.DefaultFormats
import org.json4s.ext.EnumNameSerializer
import org.json4s.native.JsonMethods._
import scalaj.http._
import scala.io.Source

class YahooFinanceService {
  def getHistoricalQuotes(tickers: List[YahooFinanceSymbol], startDate: DateTime, endDate: DateTime): List[YahooFinanceQuote] = {
    implicit val formats = DefaultFormats + new EnumNameSerializer(YahooFinanceSymbol)

    return Http("http://query.yahooapis.com/v1/public/yql")
        .param("q", s"select * " +
            s"from yahoo.finance.historicaldata " +
            s"where symbol in (${tickers mkString("'", "','", "'")}) " +
            s" and startDate = '${startDate.toString(ISODateTimeFormat.date())}' " +
            s" and endDate = '${endDate.toString(ISODateTimeFormat.date())}'")
      .param("format", "json")
      .param("env", "store://datatables.org/alltableswithkeys")
      .param("callback", "")
      .execute(parser = { inputStream =>
        parse(Source.fromInputStream(inputStream).getLines().mkString).extract[YahooFinanceHistoricalDataResponseWrapper]
      }).body.query.results.quote
  }
}