import mill._, scalalib._, publish._

trait MyModule extends PublishModule {
  def publishVersion = "0.0.1"

  def pomSettings = PomSettings(
    description = "IP",
    organization = "com.ofenbeck",
    url = "https://github.com/GeorgOfenbeck/InterviewPrep",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("GeorgOfenbeck", "Interviewprep"),
    developers = Seq(Developer("ofenbeck", "Georg Ofenbeck", "https://github.com/GeorgOfenbeck"))
  )
}

import mill._, scalalib._

object java extends JavaModule {

} 


object scala extends ScalaModule {
  def scalaVersion = "3.3.3"
  def ammoniteVersion = "3.0.0-M1"

  // You can have arbitrary numbers of third-party dependencies
  def ivyDeps = Agg(
    ivy"ch.qos.logback:logback-classic:1.2.12",
    //add the dependencies for jgit 
  )

  // Additional Scala compiler options, e.g. to turn warnings into errors
  def scalacOptions: T[Seq[String]] = Seq(
    "-deprecation",
    "-feature",
    "-no-indent",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    //"-Yexplicit-nulls",
    "-Ysafe-init",
  )
}