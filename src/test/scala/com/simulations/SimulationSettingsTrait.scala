package com.simulations

import com.helpers.Common
import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.core.filter.WhiteList
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * Created by Juri on 02/10/2019.
  */

trait SimulationSettingsTrait {

  val environmentUnderTest = Common.prop("sut", "live")
  val vU1 = Common.prop("vu1", 5)
  val vU2 = Common.prop("vu2", 1)
  val vU3 = Common.prop("vu3", 1)
  val vU4 = Common.prop("vu4", 1)
  val vU5 = Common.prop("vu5", 1)

  val singleRun = Common.prop("single_run", false)
  val rampDurationMinutes = Common.prop("ramp_duration", 1)
  val paceDurationMinutes = Common.prop("pace_duration", 2)
  val runDurationMinutes = Common.prop("run_duration", 6)
  val warmUpTimeSeconds = Common.prop("warm_up_time", 4)

  val conf = ConfigFactory.load("environment.conf")

  def rampProfile(scenarioVU: Int) : List[OpenInjectionStep with Product with Serializable] =
    List(
      nothingFor(warmUpTimeSeconds seconds),
      rampUsers(scenarioVU) during (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU) during (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU) during (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU / 2) during (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU / 2) during (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes),
      rampUsers(scenarioVU / 2) during (rampDurationMinutes minutes),
      nothingFor(paceDurationMinutes minutes)
    )


  def httpProtocol = http
    .warmUp("http://www.google.com")

  //  .proxy(Proxy("localhost", 8888)
  //    .httpsPort(8888))

    .inferHtmlResources(
      WhiteList(Seq("http[s]?://.*/test.staticcache.org/.*" , "http[s]?://.*/test2.staticcache.org/.*")),
      BlackList("http[s]?://.*/metrics.testsite.com.*")
    )
    .silentResources
}

