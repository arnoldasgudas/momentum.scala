import model.YahooFinanceQuote
import model.YahooFinanceSymbol.YahooFinanceSymbol
import spray.json.{DefaultJsonProtocol, JsObject, JsString, JsValue, JsonFormat}

object MomentumJsonProtocol extends DefaultJsonProtocol {

  implicit object YahooFinanceSymbolFormat extends JsonFormat[YahooFinanceSymbol]{
    def write (obj: YahooFinanceSymbol): JsValue = {
      JsObject(("Symbol", JsString(obj.toString)))
    }

    def read(json:JsValue):YahooFinanceSymbol = json match {
      case JsObject(fields)
        if fields.isDefinedAt("Symbol") =>
          fields("Symbol").convertTo[YahooFinanceSymbol]
    }
  }

  implicit object PricesFormat extends JsonFormat[YahooFinanceQuote] {
    def write(obj: YahooFinanceQuote): JsValue = {
      JsObject(
        ("Symbol", JsString(obj.Symbol.toString)),
        ("Date", JsString(obj.Date)),
        ("AdjClose", JsString(obj.Adj_Close))
      )
    }


    def read(json: JsValue): YahooFinanceQuote = json match {
      case JsObject(fields)
        if fields.isDefinedAt("Date") =>
        YahooFinanceQuote(fields("Symbol").convertTo[YahooFinanceSymbol], fields("Date").convertTo[String], "", "", "", "", "", "")
    }
  }
}

