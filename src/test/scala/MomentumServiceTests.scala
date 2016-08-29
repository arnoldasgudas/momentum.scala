import java.text.SimpleDateFormat

import model.{MomentumQuote, YahooConstants, YahooFinanceQuote, YahooFinanceSymbol}
import services.MomentumService
import org.scalatest.Matchers._

class MomentumServiceTests extends UnitSpec{
  test("endOfMonthQuotes should return quotes of last available day in the month"){
    val formatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)
    val expectedTicker =
      new MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 109.15)

    val actual =
    new MomentumService().endOfMonthQuotes(List(expectedTicker, new MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-28"), 100.0)))

    actual should equal (List(expectedTicker))
  }

  test("endOfMonthQuotes should return quotes of last available day in the month for all tickers"){
    val formatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)
    val expectedTickers = List(
      new MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 109.15),
      new MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-07-29"), 35.1))

    val actual =
      new MomentumService().endOfMonthQuotes(
        new MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-28"), 100.0) ::
          new MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-27"), 99.0) ::
          new MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-07-28"), 31.0) ::
          expectedTickers)

    actual.toSet should equal (expectedTickers.toSet)
  }
}
