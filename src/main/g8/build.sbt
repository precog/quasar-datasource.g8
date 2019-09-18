import scala.collection.Seq

performMavenCentralSync in ThisBuild := false   // basically just ignores all the sonatype sync parts of things

publishAsOSSProject in ThisBuild := $open_source$

homepage in ThisBuild := Some(url("https://github.com/slamdata/$name;format="lower;hyphen"$"))

scmInfo in ThisBuild := Some(ScmInfo(
  url("https://github.com/slamdata/$name;format="lower;hyphen"$"),
  "scm:git@github.com:slamdata/$name;format="lower;hyphen"$.git"))

lazy val root = project
  .in(file("."))
  .settings(noPublishSettings)
  .aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "$name;format="lower;hyphen"$",

    quasarPluginName := "$datasource_name;format="lower;hyphen"$",

    quasarPluginQuasarVersion := IO.read(file("./quasar-version")).trim,

    quasarPluginDatasourceFqcn := Some("quasar.plugin.$datasource_name;format="lower;word"$.$datasource_name;format="Camel"$DatasourceModule\$"),

    /** Specify managed dependencies here instead of with `libraryDependencies`.
      * Do not include quasar libs, they will be included based on the value of
      * `quasarPluginQuasarVersion`.
      */
    quasarPluginDependencies ++= Seq(
    ))
  .enablePlugins(AutomateHeaderPlugin, QuasarPlugin)
