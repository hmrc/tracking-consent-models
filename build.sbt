import sbt.Keys._
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

val appName = "tracking-consent-models"
val scalaVersions = Seq("2.11.12", "2.12.10")

lazy val trackingConsentModels = (project in file("."))
  .enablePlugins(PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 0,
    name := appName,
    scalaVersion := "2.11.12",
    crossScalaVersions := scalaVersions,
    PlayCrossCompilation.playCrossCompilationSettings,
    libraryDependencies ++= LibDependencies.all,
    resolvers += Resolver.typesafeRepo("releases"),
    resolvers += Resolver.bintrayRepo("hmrc", "releases"),
    makePublicallyAvailableOnBintray := true
)
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
