package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.03..
  */
trait TrackElementInfixOps {

  type TrackElementTuple = (Int, Int)

  def TrackElement(trackElementId: Int, sensorFId: Int) = (trackElementId, sensorFId)

  implicit def trackElementToInfixOps(te: Rep[TrackElementTuple])=
    TrackElementInfixOps(te)

  case class TrackElementInfixOps(te: Rep[TrackElementTuple]) {
    def trackElementId (implicit pos: SourceContext) : Rep[Int] = te._1
    def sensorFId (implicit pos: SourceContext) : Rep[Int] = te._2
  }
}
