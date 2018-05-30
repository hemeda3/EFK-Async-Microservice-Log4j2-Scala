package gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class FooSimulation extends Simulation {
  val httpProtocol = http
    .baseURL(s"http://localhost")
  val scn = scenario("foo load test")
    .exec(http("Mark").get("/foo"))
  setUp(
    scn.inject(
      atOnceUsers(1001)
    )
  ).protocols(httpProtocol)
}