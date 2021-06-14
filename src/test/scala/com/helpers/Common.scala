package com.helpers

import com.simulations.SimulationSettingsTrait
import com.typesafe.config.ConfigFactory

/**
 * Created by Juri on 02/10/2019.
 */

object Common extends SimulationSettingsTrait {


  def prop[T](key: String, defaultValue: T): T = {
    if (sys.env.contains( key )) {
      defaultValue match {
        case defaultValue: Int => sys.env( key ).toInt.asInstanceOf[T]
        case defaultValue: String => sys.env( key ).toString.asInstanceOf[T]
        case defaultValue: Boolean => sys.env( key ).toBoolean.asInstanceOf[T]
      }
    }
    else
      defaultValue
  }

  def getConfigFromFile(configFileName: String, configName: String): String = {
    val conf = ConfigFactory.load( configFileName )
    conf.getString( "properties." + environmentUnderTest + "." + configName )
  }

}
