resolvers += Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += "HMRC Releases" at "https://dl.bintray.com/hmrc/releases"

resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "2.13.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "2.2.0")

addSbtPlugin("uk.gov.hmrc"      % "sbt-play-cross-compilation" % "2.0.0"  )

addSbtPlugin("uk.gov.hmrc"      % "sbt-artifactory"            % "1.13.0" )

val playPlugin = sys.env.getOrElse("PLAY_VERSION", "2.6") match {
  case "2.6" => "com.typesafe.play" % "sbt-plugin" % "2.6.25"
  case "2.7" => "com.typesafe.play" % "sbt-plugin" % "2.7.9"
  case "2.8" => "com.typesafe.play" % "sbt-plugin" % "2.8.7"
}

addSbtPlugin(playPlugin)