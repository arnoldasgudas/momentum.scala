import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._

object Boot extends App {
  implicit val system = ActorSystem("momentum")
  val service = system.actorOf(Props[MomentumServiceActor], "momentum-rest-service")

  implicit val timeout = Timeout(60.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8182)
}
