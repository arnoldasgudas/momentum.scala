package model

class Quote(symbol: String, date: String, open: String, high: String, low: String, close: String, volume: String, adjClose: String) {
  //todo: to val
  var Symbol: String = symbol
  var Date: String = date //todo: convert to date
  var Open: String = open
  var High: String = high
  var Low: String = low
  var Volume: String = volume
  var Adj_Close: String = adjClose //todo: Change to AdjClose
}
