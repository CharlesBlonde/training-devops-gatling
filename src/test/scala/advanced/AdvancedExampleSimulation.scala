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
	val httpConf = http.baseURL(url).baseHeaders(Map())
  val durationStr:String = System.getProperty("scenario.duration","10")
  val users:String = System.getProperty("scenario.users","10")

	setUp(
		MyAnonymousScenario.scn.inject(ramp(users.toInt users) over (durationStr.toInt minutes)
      ,ramp((users.toInt*3) users) over (durationStr.toInt minutes)
      //,atOnce(50 users)
    )
  ).protocols(httpConf)
}
