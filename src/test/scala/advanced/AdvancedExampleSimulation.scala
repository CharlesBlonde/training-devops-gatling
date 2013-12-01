package advanced

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import scala.concurrent.duration._
import bootstrap._
import assertions._
import scala.sys.SystemProperties

class AdvancedExampleSimulation extends Simulation {

  val url:String = System.getProperty("scenario.url","http://localhost:8080")
	val httpConf = http.baseURL(url).baseHeaders(Map("Authorization" -> "Basic YWRtaW46YWRtaW4="))
  val durationStr:String = System.getProperty("scenario.duration","10")
  val userRate:String = System.getProperty("scenario.rate","1")

	setUp(
		SomeScenario.scn.inject(constantRate(userRate.toInt usersPerSec) during (durationStr.toInt minutes))
  ).protocols(httpConf)
		//SomeOtherScenario.otherScn.inject(nothingFor(30 seconds), ramp(5 users) over(20 seconds)).protocolConfig(httpConf))
}
