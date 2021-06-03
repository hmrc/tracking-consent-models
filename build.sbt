import sbt.Keys._
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

val appName = "tracking-consent-models"

lazy val trackingConsentModels = (project in file("."))
  .enablePlugins(PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 1,
    name := appName,
    scalaVersion := "2.12.13",
    PlayCrossCompilation.playCrossCompilationSettings,
    libraryDependencies ++= LibDependencies.all,
    resolvers += Resolver.typesafeRepo("releases"),
    isPublicArtefact := true
  )
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
