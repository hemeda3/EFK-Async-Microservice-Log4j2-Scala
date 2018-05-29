package com.gatling.simulation


class WebServiceCallSimulation extends Simulation {

  val rampUpTimeSecs = 12
  val testTimeSecs = 40
  val noOfUsers = 1000
  val minWaitMs = 2000 milliseconds
  val maxWaitMs = 5000 milliseconds

  val baseURL = "http://localhost:8080/"
  val baseName = "webservice-call-users"
  val requestName = baseName + "-request"
  val scenarioName = baseName + "-scenario"
  val URI = "/users"

  val httpConf = http
    .baseURL(baseURL)
    //.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .get(URI)
          .check(status.is(200))
      ).pause(minWaitMs, maxWaitMs)
    }

  setUp(
    scn.inject(splitUsers(100) into atOnceUsers(1) separatedBy (1000))
  ).protocols(httpConf)
}