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

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.mvc.{Cookie, RequestHeader}
import play.api.test.FakeRequest

class UserPreferencesSpec extends AnyWordSpecLike with Matchers {
  "Given a RequestHeader with no cookies, retrieving preferences" should {
    "return false for all values" in {
      implicit val rh: RequestHeader = FakeRequest()
      val userPreferences = new UserPreferences()

      userPreferences.preferences.settings should be(false)
      userPreferences.preferences.measurement should be(false)
    }
  }

  "Given a RequestHeader with cookies, retrieving preferences" should {
    "return values from the userConsent cookie" in {
      implicit val rh: RequestHeader = FakeRequest()
        .withCookies(Cookie(
          "userConsent",
          "{%22version%22:%222021.1%22%2C%22datetimeSet%22:%222021-03-16T15:49:44.741Z%22%2C%22preferences%22:{%22measurement%22:true%2C%22settings%22:true}}"
        ))
      val userPreferences = new UserPreferences()

      userPreferences.preferences.settings should be(true)
      userPreferences.preferences.measurement should be(true)

    }

    "return false for all values if a userConsent cookie is not a valid version" in {
      implicit val rh: RequestHeader = FakeRequest()
        .withCookies(Cookie(
          "userConsent",
          "{%22version%22:%22somethingwrong%22%2C%22datetimeSet%22:%222021-03-16T15:49:44.741Z%22%2C%22preferences%22:{%22measurement%22:true%2C%22settings%22:true}}"
        ))
      val userPreferences = new UserPreferences()

      userPreferences.preferences.settings should be(false)
      userPreferences.preferences.measurement should be(false)
    }

  }
}
