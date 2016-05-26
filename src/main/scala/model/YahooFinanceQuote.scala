package model

import model.YahooFinanceSymbol.YahooFinanceSymbol

case class YahooFinanceQuote(Symbol: YahooFinanceSymbol, Date: String, Open: String, High: String, Low: String, Close: String, Volume: String, Adj_Close: String)