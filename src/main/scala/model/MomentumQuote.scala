package model

import java.util.Date
import model.YahooFinanceSymbol.YahooFinanceSymbol


case class MomentumQuote(Symbol: YahooFinanceSymbol, Date: Date, AdjustedClose: BigDecimal)
