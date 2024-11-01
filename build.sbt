name := "my-spark-app"
version := "1.0"
//scalaVersion := "2.11.8"
//sparkVersion := "2.4.0"
scalaVersion := "2.12.5"
sparkVersion := "3.4.0"

sparkComponents ++= Seq("sql")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  artifact.name + "_" + sv.full + "-" + sparkVersion.value + "_" + module.revision + "." + artifact.extension
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
assemblyJarName in assembly := s"${name.value}_2.11-${sparkVersion.value}_${version.value}.jar"

parallelExecution in Test := false
fork in Test := true
