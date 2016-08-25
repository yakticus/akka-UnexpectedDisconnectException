lazy val root = (project in file(".")).
  settings(
    name := "akka-UnexpectedDisconnectException",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-http-core" % "2.4.9")
  )
