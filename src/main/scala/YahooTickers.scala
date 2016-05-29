import model.{YahooFinanceSymbol}
import services.YahooFinanceService

object YahooTickers {
  def main(args: Array[String]) : Unit = {

    val service = new YahooFinanceService
    val prices = service.getHistoricalQuotes(List(YahooFinanceSymbol.GLD, YahooFinanceSymbol.VNQ))
    println(prices)
  }
}
