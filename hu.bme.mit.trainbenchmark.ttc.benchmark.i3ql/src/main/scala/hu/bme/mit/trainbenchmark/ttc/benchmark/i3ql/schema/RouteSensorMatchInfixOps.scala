package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.04..
  */

trait RouteSensorMatchType {
  def routeId: Int
  def sensorId: Int
  def sensorRouteId: Int
  def switchPositionId: Int
  def switchId: Int
}

trait RouteSensorMatchTypeSchema {
  val IR: StructExp

  import IR._

  case class RouteSensorMatchTypeInfixOp(s: Rep[RouteSensorMatchType])  {
    def routeId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "routeId")
    def sensorId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "sensorId")
    def sensorRouteId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "sensorRouteId")
    def switchPositionId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "switchPositionId")
    def switchId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "switchId")
  }

  implicit def routeSensorMatchTypeToInfixOp (s: Rep[RouteSensorMatchType]) = RouteSensorMatchTypeInfixOp (s)
}

case class RouteSensorMatch(routeId: Int, sensorId: Int, sensorRouteId: Int, switchPositionId: Int, switchId: Int)
  extends RouteSensorMatchType{}

trait RouteSensorMatchInfixOps extends RouteSensorMatchTypeSchema {}


/*trait RouteSensorMatch {
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
}*/
