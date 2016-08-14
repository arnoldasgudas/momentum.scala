import java.text.SimpleDateFormat

import model.{YahooConstants, YahooFinanceHistoricalDataResponseWrapper, YahooFinanceSymbol}
import org.json4s._
import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.JsonMethods._


class YahooFinanceServiceTests extends UnitSpec{


  test("Json should be converted to YahooFinanceHistoricalDataResponseWrapper with correct formatter"){
    val json = parse("""
         {
           "query": {
             "count": 10,
             "created": "2016-05-26T18:57:40Z",
             "lang": "en-US",
             "results": {
               "quote": [
                 {
                   "Symbol": "GLD",
                   "Date": "2016-04-08",
                   "Open": "118.07",
                   "High": "118.769997",
                   "Low": "117.980003",
                   "Close": "118.43",
                   "Volume": "9078900",
                   "Adj_Close": "118.43"
                 },
                 {
                   "Symbol": "GLD",
                   "Date": "2016-04-07",
                   "Open": "118.650002",
                   "High": "118.849998",
                   "Low": "115.00",
                   "Close": "118.610001",
                   "Volume": "11900500",
                   "Adj_Close": "118.610001"
                 },
                 {
                   "Symbol": "GLD",
                   "Date": "2016-04-06",
                   "Open": "116.699997",
                   "High": "117.389999",
                   "Low": "116.260002",
                   "Close": "116.940002",
                   "Volume": "7549400",
                   "Adj_Close": "116.940002"
                 },
                 {
                   "Symbol": "GLD",
                   "Date": "2016-04-05",
                   "Open": "117.760002",
                   "High": "117.93",
                   "Low": "117.150002",
                   "Close": "117.660004",
                   "Volume": "8865900",
                   "Adj_Close": "117.660004"
                 },
                 {
                   "Symbol": "GLD",
                   "Date": "2016-04-04",
                   "Open": "116.669998",
                   "High": "116.730003",
                   "Low": "116.07",
                   "Close": "116.150002",
                   "Volume": "7643700",
                   "Adj_Close": "116.150002"
                 },
                 {
                   "Symbol": "VNQ",
                   "Date": "2016-04-08",
                   "Open": "83.129997",
                   "High": "83.50",
                   "Low": "82.949997",
                   "Close": "83.160004",
                   "Volume": "3370300",
                   "Adj_Close": "83.160004"
                 },
                 {
                   "Symbol": "VNQ",
                   "Date": "2016-04-07",
                   "Open": "83.019997",
                   "High": "83.220001",
                   "Low": "82.330002",
                   "Close": "82.699997",
                   "Volume": "2339400",
                   "Adj_Close": "82.699997"
                 },
                 {
                   "Symbol": "VNQ",
                   "Date": "2016-04-06",
                   "Open": "83.050003",
                   "High": "83.260002",
                   "Low": "82.589996",
                   "Close": "83.220001",
                   "Volume": "2940500",
                   "Adj_Close": "83.220001"
                 },
                 {
                   "Symbol": "VNQ",
                   "Date": "2016-04-05",
                   "Open": "83.199997",
                   "High": "83.419998",
                   "Low": "82.809998",
                   "Close": "83.019997",
                   "Volume": "3251300",
                   "Adj_Close": "83.019997"
                 },
                 {
                   "Symbol": "VNQ",
                   "Date": "2016-04-04",
                   "Open": "83.860001",
                   "High": "83.940002",
                   "Low": "83.279999",
                   "Close": "83.57",
                   "Volume": "6908900",
                   "Adj_Close": "83.57"
                 }
               ]
             }
           }
         }""")

    implicit val formats = new DefaultFormats {
      override def dateFormatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)
    } + new EnumNameSerializer(YahooFinanceSymbol)

    val actual = json.extract[YahooFinanceHistoricalDataResponseWrapper]
    assert(actual != null)
  }
}
