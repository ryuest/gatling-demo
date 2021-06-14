package com.requestObjects

/**
 * Created by Juri on 02/10/2019.
 */

import com.helpers.Common
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

object HomePage {

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


  def getESTLottoHomePage =
    exec( http( "Get Home page" )
      .get( homePage + "/" )
      .headers( headersAll )
      .check( status.is( 200 ) )
      .check( headerRegex( "set-cookie", "JSESSIONID=([^;]+)" ).exists.saveAs( "session_id" ) )
      .check( regex( "org.apache.struts.action.TOKEN=(.*?)\"" ).saveAs( "action_token" ) )
      .check( regex( "org.apache.struts.taglib.html.TOKEN\" value=(.*?)\"" ).saveAs( "html_token" ) )
    )
      .exec( session => {
        LoggerFactory.getLogger( "action_token: " + session( "action_token" ).as[String] ).info( "logging info" )
        LoggerFactory.getLogger( "html_token: " + session( "html_token" ).as[String] ).info( "logging info" )
        session
      } )
      .pause( 1, 3 )


  def lottoPage =
    exec( http( "Go Lotto page" )
      .get( homePage + "${location}" )
      .headers( headersAll )
      .check( status.is( 200 ) )
    )
      .pause( 1, 3 )


}
