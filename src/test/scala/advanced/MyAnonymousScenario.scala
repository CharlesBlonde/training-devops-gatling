package advanced

import io.gatling.core.Predef._
import io.gatling.core.session.Session
import io.gatling.http.Predef._
import io.gatling.http.request.StringBody
import scala.concurrent.duration._
import bootstrap._
import assertions._


object MyAnonymousScenario {
  val users = csv("logins.csv").circular

  val scn = {
    scenario("Customer")
      .feed(users)
      .exec(
      http("HomePage")
        .get("/")
        .check(status.is(200))
        .check(xpath("//input[@name='form_build_id']/@value'").saveAs("form_id"))
    ).pause(1)
    .exec(
      http("LoginPage")
        .post("/")
        .param("name","${username}")
        .param("pass","StressTest12345")
        .param("form_build_id","${form_id}")
        .param("form_id","user_login")
        .param("op","Log in")
        .check(status.is(200))
        .check(regex("Today’s Offers"))
    ).pause(3)
    .exec(
      http("OfferPage")
        .get("/node/390/lightbox2")
        .check(status.is(200))
        .check(regex("Book Id"))
    )/*.exec(
      http("PurchasePromises")
      .get("/node/add/book-purchase-promises/390")
      .check(status.is(200))
    )*/
  }

}