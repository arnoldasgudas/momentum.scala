package model

import model.YahooFinanceSymbol.YahooFinanceSymbol

case class MomentumResponse(symbol: YahooFinanceSymbol, value: BigDecimal)
