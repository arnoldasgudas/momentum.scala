import java.text.SimpleDateFormat

import model._
import services.MomentumService
import org.scalatest.Matchers._

class MomentumServiceTests extends UnitSpec{
  val formatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)

  test("endOfMonthQuotes should return quotes of last available day in the month"){
    val expectedTicker =
      MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 109.15)

    val actual =
    MomentumService.endOfMonthQuotes(List(expectedTicker, MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-28"), 100.0)))

    actual should equal (List(expectedTicker))
  }

  test("endOfMonthQuotes should return quotes of last available day in the month for all tickers"){
    val expectedTickers = List(
      MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 109.15),
      MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-07-29"), 35.1))

    val actual =
      MomentumService.endOfMonthQuotes(
        MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-28"), 100.0) ::
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-27"), 99.0) ::
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-07-28"), 31.0) ::
          expectedTickers)

    actual.toSet should equal (expectedTickers.toSet)
  }

  test("calculateMomentum should add one, three, six, nine and twelve month momentum"){
    val actual =
      MomentumService.calculateMomentum(
        List(
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 129.0),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-06-30"), 126.5),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-05-31"), 116.1),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-04-29"), 123.7),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-03-31"), 117.6),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-02-29"), 118.6),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-01-29"), 106.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-12-31"), 101.5),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-11-30"), 101.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-10-30"), 109.3),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-09-30"), 106.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-08-31"), 108.8),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-07-31"), 104.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-06-30"), 112.4)))

    actual should equal (List(MomentumResponse(YahooFinanceSymbol.GLD, 5.68)))
  }

  test("calculateMomentum should add one, three, six, nine and twelve month momentum for multiple tickers"){
    val actual =
      MomentumService.calculateMomentum(
        List(
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 129.0),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-06-30"), 126.5),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-05-31"), 116.1),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-04-29"), 123.7),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-03-31"), 117.6),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-02-29"), 118.6),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-01-29"), 106.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-12-31"), 101.5),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-11-30"), 101.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-10-30"), 109.3),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-09-30"), 106.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-08-31"), 108.8),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-07-31"), 104.9),
          MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2015-06-30"), 112.4),

          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-07-29"), 92.4),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-06-30"), 88.7),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-05-31"), 82.9),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-04-29"), 81.1),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-03-31"), 83.1),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-02-29"), 75.2),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2016-01-29"), 75.5),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-12-31"), 78.1),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-11-30"), 76.7),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-10-30"), 77.2),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-09-30"), 73.0),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-08-31"), 70.9),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-07-31"), 75.6),
          MomentumQuote(YahooFinanceSymbol.VNQ, formatter.parse("2015-06-30"), 71.5)))

    actual should equal (List(MomentumResponse(YahooFinanceSymbol.VNQ, 5.82),MomentumResponse(YahooFinanceSymbol.GLD, 5.68)))
  }
}
