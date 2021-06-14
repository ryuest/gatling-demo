package com.simulations

import scala.concurrent.duration._
import io.gatling.core.Predef._
import com.scenarios.LottoLogin._

/**
 * Created by Juri on 02/10/2019.
 */

class LottoLogin extends Simulation with SimulationSettingsTrait{

    setUp(lottoLogin.inject(rampProfile(vU1)).protocols(httpProtocol)).maxDuration(runDurationMinutes minutes)

}
