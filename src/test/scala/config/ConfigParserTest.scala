package de.portfolio
package config

import com.typesafe.config.ConfigFactory
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._


class ConfigParserTest extends AnyFunSuite {
  val myConfig = ConfigFactory.load() // file is in "src/test/resources"

  test("getAppVersion should return correct result") {
    val configParser = new ConfigParser {
      override lazy val config = myConfig
    }
    configParser.getAppVersion should be("1.0.0")
  }

  test("getAppName should return correct result") {
    val configParser = new ConfigParser {
      override lazy val config = myConfig
    }
    configParser.getAppName should be("DE-portfolio by bdelpozo")
  }

  test("getServerName should return correct result") {
    val configParser = new ConfigParser {
      override lazy val config = myConfig
    }
    configParser.getServerName should be("localhost:9092")
  }

  test("getBDUrlProd should return correct result") {
    val configParser = new ConfigParser {
      override lazy val config = myConfig
    }
    configParser.getBDUrlProd should be("jdbc:mysql://localhost:3306/ecommerce")
  }

  test("getAvailableEnv should return correct result") {
    val configParser = new ConfigParser {
      override lazy val config = myConfig
    }
    assertResult(Array("DEV", "PRE", "PRO"))(configParser.getAvailableEnv.toArray)
  }

}
