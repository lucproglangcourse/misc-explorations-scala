name := "misc-explorations-scala"

version := "0.2"

libraryDependencies ++= Seq(
  "org.scalaz"     %% "scalaz-core" % "7.4.0-M17",
  "org.scalatest"  %% "scalatest"   % "3.2.20"    % Test,
  "org.scalacheck" %% "scalacheck"  % "1.20.0"    % Test
)

console / initialCommands := """

                             |import scalaz._

                             |import Scalaz._

                             |""".stripMargin
