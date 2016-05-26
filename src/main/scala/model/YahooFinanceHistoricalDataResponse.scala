package model

class YahooFinanceHistoricalDataResponse(resultCount: Int, resultCreated: String, language: String, data: YahooFinanceHistoricalDataWrapper) {
  //todo: change to val
  var count: Int = resultCount
  var created: String = resultCreated
  var lang: String = language
  var results: YahooFinanceHistoricalDataWrapper = data
}
