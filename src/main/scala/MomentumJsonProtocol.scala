import java.text.SimpleDateFormat
import java.util.Date

import model.{MomentumQuote, YahooConstants, YahooFinanceQuote}
import model.YahooFinanceSymbol.YahooFinanceSymbol
import spray.json.{DefaultJsonProtocol, JsObject, JsString, JsValue, JsonFormat}

object MomentumJsonProtocol extends DefaultJsonProtocol {
  implicit object YahooDateFormat extends JsonFormat[Date]{
    override def read(json: JsValue): Date = json match {
      case JsObject(fields)
        if fields.isDefinedAt("Date") =>
          fields("Date").convertTo[Date]
    }

    override def write(obj: Date): JsValue = {
      val formatter = new SimpleDateFormat(YahooConstants.DATE_FORMAT)
      JsObject(("Date", JsString(formatter.format(obj))))
    }
  }

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
        ("AdjClose", JsString(obj.Adj_Close)),
        ("Date",  JsString(new SimpleDateFormat(YahooConstants.DATE_FORMAT).format(obj.Date)))
      )
    }


    def read(json: JsValue): YahooFinanceQuote = json match {
      case JsObject(fields)
        if fields.isDefinedAt("Date") =>
        YahooFinanceQuote(fields("Symbol").convertTo[YahooFinanceSymbol], fields("Date").convertTo[Date], "", "", "", "", "", "")
    }
  }

  implicit object MomentumQuoteFormat extends JsonFormat[MomentumQuote] {
    def write(obj: MomentumQuote): JsValue = {
      JsObject(
        ("Symbol", JsString(obj.Symbol.toString)),
        ("AdjustedClose", JsString(obj.AdjustedClose.toString())),
        ("Date",  JsString(new SimpleDateFormat(YahooConstants.DATE_FORMAT).format(obj.Date)))
      )
    }


    def read(json: JsValue): MomentumQuote = json match {
      case JsObject(fields)
        if fields.isDefinedAt("Date") =>
        MomentumQuote(
          fields("Symbol").convertTo[YahooFinanceSymbol],
          fields("Date").convertTo[Date],
          BigDecimal(fields("AdjustedClose").convertTo[String]))
    }
  }
}

