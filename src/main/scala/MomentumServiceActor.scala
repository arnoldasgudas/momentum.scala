import akka.actor.Actor
import model.{MomentumQuote, YahooFinanceSymbol}
import org.joda.time.DateTime
import services.{MomentumService, YahooFinanceService}
import spray.http.MediaTypes._
import spray.routing._
import spray.json._
import MomentumJsonProtocol._
import model.YahooFinanceSymbol.YahooFinanceSymbol

class MomentumServiceActor extends Actor with HttpService{
  def receive = runRoute(momentumRoute)

  def actorRefFactory = context

  val momentumRoute = {
    path(""){
      get{
        respondWithMediaType(`application/json`) {
          complete {
            //todo: pass dates
            val startDate = new DateTime("2015-07-01")
            val endDate = new DateTime("2016-09-30")
            val tickers = List(YahooFinanceSymbol.GLD, YahooFinanceSymbol.VNQ)

            val momentums = MomentumService.calculateMomentum(
              MomentumService.endOfMonthQuotes(
                tickers.flatten(
                  YahooFinanceService.getHistoricalQuotes(_, startDate, endDate)
                    //todo: do we really need map?
                    .map(_.toMomentumQuote))
                ))
            //todo: sort momentums
            momentums.toJson.toString()
          }
        }
      }
    }
  }
}
