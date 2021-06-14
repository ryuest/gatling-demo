package com.requestObjects

import com.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.helpers.Common._
import org.slf4j.LoggerFactory

object Login {

  val homePage: String = Common.getConfigFromFile( "environment.conf", "homePage" )


  val headersAll = Map(
    HttpHeaderNames.AcceptEncoding -> "gzip, deflate, br",
    HttpHeaderNames.UserAgent -> "${UAStrings}",
    HttpHeaderNames.Accept -> "*/*",
    HttpHeaderNames.Connection -> "keep-alive",
    HttpHeaderNames.AcceptLanguage -> "en-US,en;q=0.9,et;q=0.8,ru;q=0.7,es;q=0.6,ca;q=0.5",
    HttpHeaderNames.ContentType -> "application/x-www-form-urlencoded",
    HttpHeaderNames.Origin -> homePage,
    HttpHeaderNames.Host -> "www.eestiloto.ee"
  )


  def loginLotto =
    exec( http( "Post Login Lotto" )
      .post( homePage + "/" + "osi.do" )
      .queryParam( "org.apache.struts.action.TOKEN", "${action_token}" )
      .formParam( "username", "${username}" )
      .formParam( "password", "${password}" )
      .formParam( "org.apache.struts.taglib.html.TOKEN", "${html_token}" )
      .formParam( "page", "1" )
      .formParam( "action", "login" )
      .headers( headersAll )
      .disableFollowRedirect
      .check( status.in( 200, 302 ) )
      .check( headerRegex( "set-cookie", "JSESSIONID=([^;]+)" ).exists.saveAs( "session_id" ) )
      .check( headerRegex( "Location", "(.*)" ).saveAs( "location" ) )
    )
      .exec( session => {
        LoggerFactory.getLogger( "HERE <<< ---- location: " + session( "location" ).as[String] ).info( "logging info" )
        session
      } )
}