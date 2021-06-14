package com.scenarios

import com.helpers.Common
import com.requestObjects.{HomePage, Login}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * Created by Juri on 02/10/2019.
 */

object LottoLogin {

  val ualistfeeder = csv( "UADesktop.csv" )
  val usersfeeder = csv( "UserAccounts" + Common.environmentUnderTest.toUpperCase + ".csv" )

  val lottoLogin =
    scenario( "Lotto Login" )
      .forever {
        exec( session => session.reset )
          .exec( flushHttpCache )
          .exec( flushCookieJar )
          .exec( flushSessionCookies )
          .feed( ualistfeeder.random.circular )
          .feed( usersfeeder.random.circular )
          .exec(
            HomePage.getESTLottoHomePage,
            Login.loginLotto,
            HomePage.lottoPage
          )
      }
}

