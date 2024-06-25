package de.portfolio
package config

import com.typesafe.config.Config

import java.util

class ConfigParser {
  lazy val config: Config = getConfig

  // It returns all the config
  def getConfig: com.typesafe.config.Config = {
    // To load the config we use ConfigFactory.load()
    import com.typesafe.config.ConfigFactory
    ConfigFactory.load()
  }

  // To return the value of key "app.version" like String
  def getAppVersion: String = config.getString("app.version")

  // To return the value of key "app.name" like String overwritten by application.conf
  def getAppName: String = config.getString("app.name")

  // To return the value of key "kafka.broker.server" like String from reference.conf
  def getServerName: String = config.getString("kafka.broker.server")

  // To return the value of key "bd.prod.url" from other.conf included in reference.conf
  def getBDUrlProd: String = config.getString("bd.prod.url")

  def getAvailableEnv: util.List[String] = config.getStringList("available_env")
}

object ConfigParser {
  def apply(): ConfigParser = new ConfigParser()
}

object Main extends App {
  val configParser = ConfigParser()
  println(configParser.getAppVersion)
  println(configParser.getAppName)
  println(configParser.getServerName)
  println(configParser.getBDUrlProd)
  println(configParser.getAvailableEnv)
}
