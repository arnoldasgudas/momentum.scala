import akka.actor.Actor
import model.YahooFinanceSymbol
import org.joda.time.DateTime
import services.YahooFinanceService
import spray.http.MediaTypes._
import spray.routing._
import spray.json._
import MomentumJsonProtocol._

class MomentumServiceActor extends Actor with HttpService{
  def receive = runRoute(momentumRoute)

  def actorRefFactory = context

  val momentumRoute = {
    path(""){
      get{
        respondWithMediaType(`application/json`) {
          complete {
            val service = new YahooFinanceService

            //todo: pass dates
            val startDate = new DateTime("2016-04-04")
            val endDate = new DateTime("2016-04-08")

            val prices = service.getHistoricalQuotes(List(YahooFinanceSymbol.GLD, YahooFinanceSymbol.VNQ), startDate, endDate)
            prices.toJson.toString()
          }
        }
      }
    }
  }
}
