package advanced

import io.gatling.core.Predef._
import io.gatling.core.session.Session
import io.gatling.http.Predef._
import io.gatling.http.request.StringBody
import scala.concurrent.duration._
import bootstrap._
import assertions._

object SomeScenario {

  val scn = {
    scenario("Customer")
      .exec(
      http("GetCustomer")
        .post("/services/v1.0/customerService")
        .body(StringBody( s"""
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://ws.xebia.fr/customer/v1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:getCustomer>
         <v1:id>2</v1:id>
      </v1:getCustomer>
   </soapenv:Body>
</soapenv:Envelope>"""))
        .check(status.is(200))
    ).exec(
      http("zeNoisyOperation")
        .post("/services/v1.0/customerService")
        .body(StringBody( s"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://ws.xebia.fr/customer/v1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:zeNoisyOperation>
         <v1:id>5</v1:id>
      </v1:zeNoisyOperation>
   </soapenv:Body>
</soapenv:Envelope>
     """))

    ).exec(
      http("zeSlowOperation")
        .post("/services/v1.0/customerService")
        .body(StringBody( s"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://ws.xebia.fr/customer/v1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:zeSlowOperation>
         <v1:id>1</v1:id>
      </v1:zeSlowOperation>
   </soapenv:Body>
</soapenv:Envelope>
     """))

    ).exec(
      http("zeVerySlowAggregatingOperation")
        .post("/services/v1.0/customerService")
        .body(StringBody( s"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://ws.xebia.fr/customer/v1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:zeVerySlowAggregatingOperation>
         <v1:id>1</v1:id>
      </v1:zeVerySlowAggregatingOperation>
   </soapenv:Body>
</soapenv:Envelope>
     """))

    ).exec(
      http("zeJmsOperation")
        .post("/services/v1.0/customerService")
        .body(StringBody( s"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://ws.xebia.fr/customer/v1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:zeJmsOperation>
         <v1:in>1</v1:in>
      </v1:zeJmsOperation>
   </soapenv:Body>
</soapenv:Envelope>
     """))

    ).exec(
      http("zeBuggyOperation")
        .post("/services/v1.0/customerService")
        .body(StringBody( s"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v1="http://ws.xebia.fr/customer/v1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v1:zeBuggyOperation>
         <v1:id>1</v1:id>
      </v1:zeBuggyOperation>
   </soapenv:Body>
</soapenv:Envelope>
     """))

    )

  }

}