import scalaj.http._

object YahooTickers {
  def main(args: Array[String]) : Unit = {

    val response: HttpResponse[String] = Http("http://query.yahooapis.com/v1/public/yql")
      .param("q","select * from yahoo.finance.historicaldata where symbol in (\"GLD\", \"VNQ\") and startDate = \"2016-04-04\" and endDate = \"2016-04-08\"")
      .param("format","json")
      .param("env","store://datatables.org/alltableswithkeys")
      .param("callback","")
      .asString

    println(response.body)
  }
}
