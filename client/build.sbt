lazy val root = (project in file(".")).
  settings(
    name := "scalaj-http-example",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq("org.scalaj" %% "scalaj-http" % "2.3.0")
  )
