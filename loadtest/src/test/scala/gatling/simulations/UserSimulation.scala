package gatling.simulations


import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class UserSimulation extends Simulation {

  before {
    println("***** User simulation is about to begin! *****")
  }

  after {
    println("***** User simulation has ended! ******")
  }


  val httpProtocol = http
    .baseURL(s"http://localhost:8080")
  val findUserScenario = scenario("Find Users load Test")
    .exec(http("find User").get("/users?")
      .header("Content-Type", "application/json")
      .queryParam("role", "foo")
      .check(status.is(200))
      .check(jsonPath("$..id").is("1"))
      .check(jsonPath("$..name").is("john"))
      .check(jsonPath("$..role").is("foo")))
  val jsonFileFeeder = jsonFile("data1000000.json")

  val saveUserScinario = scenario("Save Users Load Test")
    .feed(jsonFileFeeder)
    .exec(http(" save user")
      .post("/users")
      .header("Content-Type", "application/json")
      .body(StringBody("""{ "name": "${name}","role": "${role}" }""")).asJSON
      .check(status.is(201))).pause(1)
  setUp(
    saveUserScinario.inject(
      rampUsers(1000) over (50 seconds)
    ),
    findUserScenario.inject(
      rampUsers(1000) over (50 seconds)
    )

  ).protocols(httpProtocol)


}