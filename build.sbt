import sbt.Keys._
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

val scala2_12 = "2.12.13"
val scala2_13 = "2.13.7"

val appName = "tracking-consent-models"

lazy val trackingConsentModels = (project in file("."))
  .enablePlugins(PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 1,
    name := appName,
    scalaVersion := scala2_12,
    crossScalaVersions := Seq(scala2_12, scala2_13),
    libraryDependencies ++= LibDependencies.all,
    resolvers += Resolver.typesafeRepo("releases"),
    isPublicArtefact := true
  )
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
