package services

import java.text.SimpleDateFormat

import model.YahooFinanceSymbol.YahooFinanceSymbol
import model._
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import org.json4s.DefaultFormats
import org.json4s.ext.EnumNameSerializer
import org.json4s.native.JsonMethods._

import scalaj.http._
import scala.io.Source

object YahooFinanceService {
  def getHistoricalQuotes(ticker: YahooFinanceSymbol, startDate: DateTime, endDate: DateTime): List[YahooFinanceQuote] = {
    implicit val formats = new DefaultFormats {
      override def dateFormatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)
    } + new EnumNameSerializer(YahooFinanceSymbol)

    Http("http://query.yahooapis.com/v1/public/yql")
      .timeout(10*1000,60*1000)
      .param("q", s"select Symbol, Date, Adj_Close " +
        s"from yahoo.finance.historicaldata " +
        s"where symbol = '${ticker.toString}' " +
        s" and startDate = '${startDate.toString(ISODateTimeFormat.date())}' " +
        s" and endDate = '${endDate.toString(ISODateTimeFormat.date())}'")
      .param("format", "json")
      .param("env", "store://datatables.org/alltableswithkeys")
      .param("callback", "")
      .execute(parser = { inputStream =>
        parse(Source.fromInputStream(inputStream).getLines().mkString)
          .extract[YahooFinanceHistoricalDataResponseWrapper]
      }).body
      .query
      .results.getOrElse(YahooFinanceHistoricalDataWrapper(List.empty[YahooFinanceQuote]))
      .quote
  }
}