import java.text.SimpleDateFormat

import model.{MomentumQuote, YahooConstants, YahooFinanceQuote, YahooFinanceSymbol}
import services.MomentumService

class MomentumServiceTests extends UnitSpec{
  test("endOfMonthTickers should return only tickers of last available day in the month"){
    val formatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)
    val expectedTicker =
      new MomentumQuote(YahooFinanceSymbol.GLD, formatter.parse("2016-07-29"), 109.15)

    assert(new MomentumService().endOfMonthTickers(List(expectedTicker)).head.Date.equals(expectedTicker.Date))
  }
}
