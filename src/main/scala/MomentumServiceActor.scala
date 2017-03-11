import akka.actor.Actor
import model.YahooFinanceSymbol
import org.joda.time.DateTime
import services.{MomentumService, YahooFinanceService}
import spray.http.MediaTypes._
import spray.routing._
import spray.json._
import MomentumJsonProtocol._
import spray.httpx.marshalling.ToResponseMarshallable
import spray.routing.directives.ContentTypeResolver.Default

class MomentumServiceActor extends Actor with HttpService{
  def receive = runRoute(momentumRoute)

  def actorRefFactory = context

  val tickers = List(
    YahooFinanceSymbol.GLD,
    YahooFinanceSymbol.VNQ,
    YahooFinanceSymbol.EMB,
    YahooFinanceSymbol.BWX,
    YahooFinanceSymbol.DBC,
    YahooFinanceSymbol.IEF,
    YahooFinanceSymbol.RWX,
    YahooFinanceSymbol.SPY,
    YahooFinanceSymbol.VGK,
    YahooFinanceSymbol.VPL,
    YahooFinanceSymbol.VWO)

  val momentumRoute = {
    path(""){
      get{
        getFromResource("main.html")
      }
    } ~
    path("current"){
      get{
        respondWithMediaType(`application/json`) {
          complete {
            val endDate = new DateTime()
            val startDate = endDate.minusDays(457)

            getMomentumResponse(endDate, startDate)
          }
        }
      }
    } ~
    path("lastmonth"){
      get{
        respondWithMediaType(`application/json`){
          complete{
            val endDate = new DateTime().minusMonths(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay()
            val startDate = endDate.minusDays(457)

            getMomentumResponse(endDate, startDate)
          }
        }
      }
    }
  }

  def getMomentumResponse(endDate: DateTime, startDate: DateTime): ToResponseMarshallable = {
    MomentumService.calculateMomentum(
      MomentumService.endOfMonthQuotes(
        tickers.flatten(
          YahooFinanceService.getHistoricalQuotes(_, startDate, endDate)
            .map(_.toMomentumQuote()))
      )).toJson.toString()
  }
}
