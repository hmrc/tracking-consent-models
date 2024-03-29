> **Note**
> this repository has been archived because no-one was using it, you can still use the code as a reference if you need to check for a users tracking consent preference within your service. If you have any questions, reach out to #team-plat-ui in the hmrc digital slack

# tracking-consent-models

This is a lightweight library to decode a `userConsent` cookie as generated by the microservice
[tracking-consent-frontend](https://github.com/hmrc/tracking-consent-frontend)

## Usage

Add the following line to your project dependencies, replacing `xx.xx.xxx-play-28` with the 
[latest release](https://github.com/hmrc/tracking-consent-models/releases) and your project version of Play.
```
"uk.gov.hmrc" %% "tracking-consent-models" % "xx.xx.xx-play-28"
```

To read a user preferences from the `userConsent` cookie, create a `UserPreferences` object, which exposes the following
methods return Boolean:
```
userPreferences.preferences.measurement
userPreferences.preferences.settings
```

Example usage:
```
package uk.gov.hmrc.helpfrontend.controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import uk.gov.hmrc.play.bootstrap.frontend.controller.FrontendController
import uk.gov.hmrc.helpfrontend.views.html.TermsAndConditionsPage
import uk.gov.hmrc.trackingconsent.models.UserPreferences

class SomeController @Inject() (
  mcc: MessagesControllerComponents,
  termsAndConditionsPage: TermsAndConditionsPage,
  userPreferences: UserPreferences
) extends FrontendController(mcc) {

  private val themeCookie = 
    Cookie(name = "theme", value = "dark", maxAge = Some(365.days.toSeconds.toInt))

  private def allowedCookies(implicit request: RequestHeader) =
    if (userPreferences.preferences.settings) Seq(themeCookie) else Seq.empty

  val termsAndConditions: Action[AnyContent] = Action { implicit request =>
    Ok(termsAndConditionsPage()).withCookies(allowedCookies: _*)
  }
}
```

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
