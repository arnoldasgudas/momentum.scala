package services

import model.YahooFinanceSymbol.YahooFinanceSymbol
import model.{YahooFinanceHistoricalDataResponseWrapper, YahooFinanceQuote, YahooFinanceSymbol}
import org.json4s.DefaultFormats
import org.json4s.ext.EnumNameSerializer
import org.json4s.native.JsonMethods._

import scalaj.http._
import scala.io.Source
import scalaj.http.HttpResponse

class YahooFinanceService {
  def getHistoricalQuotes(tickers: List[YahooFinanceSymbol]): List[YahooFinanceQuote] = {
    implicit val formats = DefaultFormats + new EnumNameSerializer(YahooFinanceSymbol)

    //todo: pass dates
    val startDate = "2016-04-04"
    val endDate = "2016-04-08"

    val response: HttpResponse[YahooFinanceHistoricalDataResponseWrapper] = Http("http://query.yahooapis.com/v1/public/yql")
        .param("q",
          s"select * " +
          s"from yahoo.finance.historicaldata " +
          s"where symbol in (${tickers mkString("'", "','", "'")}) and startDate = '$startDate' and endDate = '$endDate'")
      .param("format", "json")
      .param("env", "store://datatables.org/alltableswithkeys")
      .param("callback", "")
      .execute(parser = { inputStream =>
        parse(Source.fromInputStream(inputStream).getLines().mkString).extract[YahooFinanceHistoricalDataResponseWrapper]
      })

    return response.body.query.results.quote
  }
}