package model

import java.util.Date

import model.YahooFinanceSymbol.YahooFinanceSymbol

case class YahooFinanceQuote(Symbol: YahooFinanceSymbol, Date: Date, Open: String, High: String, Low: String, Close: String, Volume: String, Adj_Close: String)