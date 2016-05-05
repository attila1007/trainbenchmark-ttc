package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._

import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.04..
  */
trait RouteSensorMatch {
  type RouteSensorMatchTuple = (Int, Int,Int,Int)

  def RouteSensorMatch(routeId: Int, sensorId: Int, switchPositionId: Int, switchId: Int) =
    (routeId, sensorId, switchPositionId, switchId)

  implicit def RouteSensorMatchToInfixOps(rsm: Rep[RouteSensorMatchTuple]) =
    RouteSensorMatchInfixOps(rsm)

  case class RouteSensorMatchInfixOps(rsm: Rep[RouteSensorMatchTuple]) {
    def routeId (implicit pos: SourceContext): Rep[Int] = rsm._1
    def sensorId (implicit pos: SourceContext): Rep[Int] = rsm._2
    def switchPositionId (implicit pos: SourceContext): Rep[Int] = rsm._3
    def switchId (implicit pos: SourceContext): Rep[Int] = rsm._4
  }
}
