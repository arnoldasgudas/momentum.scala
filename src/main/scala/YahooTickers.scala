import model.{YahooFinanceHistoricalDataResponseWrapper, YahooFinanceSymbol}
import org.json4s.ext.EnumNameSerializer
import scalaj.http._
import org.json4s._
import org.json4s.native.JsonMethods._
import scala.io.Source

object YahooTickers {
  def main(args: Array[String]) : Unit = {

    implicit val formats = DefaultFormats + new EnumNameSerializer(YahooFinanceSymbol)

    val response: HttpResponse[YahooFinanceHistoricalDataResponseWrapper] = Http("http://query.yahooapis.com/v1/public/yql")
      .param("q","select * from yahoo.finance.historicaldata where symbol in (\"GLD\", \"VNQ\") and startDate = \"2016-04-04\" and endDate = \"2016-04-08\"")
      .param("format","json")
      .param("env","store://datatables.org/alltableswithkeys")
      .param("callback","")
      .execute(parser = {inputStream =>
          parse(Source.fromInputStream(inputStream).getLines().mkString).extract[YahooFinanceHistoricalDataResponseWrapper]})

    println(response.body)
  }
}
