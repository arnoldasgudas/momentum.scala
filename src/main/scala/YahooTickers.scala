import java.util.Date

import model.YahooFinanceSymbol
import org.joda.time.DateTime
import services.YahooFinanceService

object YahooTickers {
  def main(args: Array[String]) : Unit = {

    val service = new YahooFinanceService

    //todo: pass dates
    val startDate = new DateTime("2016-04-04")
    val endDate = new DateTime("2016-04-08")

    val prices = service.getHistoricalQuotes(List(YahooFinanceSymbol.GLD, YahooFinanceSymbol.VNQ), startDate, endDate)
    println(prices)
  }
}
