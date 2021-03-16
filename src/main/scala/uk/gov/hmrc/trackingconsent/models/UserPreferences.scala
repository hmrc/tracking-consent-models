/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.trackingconsent.models

import play.api.libs.json.Json
import play.api.mvc.RequestHeader

import java.net.URLDecoder

class UserPreferences() {
  def preferences(implicit rh: RequestHeader): Preferences = {
    rh.cookies.get("userConsent") flatMap { userConsentCookie =>
      val decodedCookie: String = URLDecoder.decode(userConsentCookie.value, "UTF-8")
      Json.parse(decodedCookie)
        .asOpt[UserConsent]
        .collect { case userConsent if userConsent.version == "2021.1" => userConsent.preferences }
    } getOrElse Preferences(measurement = false, settings = false)
  }

}
