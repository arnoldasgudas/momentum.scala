package model

import java.util.Date

import model.YahooFinanceSymbol.YahooFinanceSymbol

case class YahooFinanceQuote(Symbol: YahooFinanceSymbol, Date: Date, Adj_Close: String){
  def toMomentumQuote() : MomentumQuote ={
    MomentumQuote(Symbol, Date, BigDecimal(Adj_Close))
  }
}