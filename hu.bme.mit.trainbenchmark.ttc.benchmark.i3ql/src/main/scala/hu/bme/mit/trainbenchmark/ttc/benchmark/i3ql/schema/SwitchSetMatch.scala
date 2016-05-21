package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.04..
  */

trait SwitchSetMatchType {
  def semaphoreId: Int
  def routeId: Int
  def switchPositionId: Int
  def position: Int
  def switchId: Int
  def currentPosition: Int
}

trait SwitchSetMatchTypeSchema {
  val IR: StructExp

  import IR._

  case class SwitchSetMatchTypeInfixOp(s: Rep[SwitchSetMatchType])  {
    def semaphoreId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "semaphoreId")
    def routeId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "routeId")
    def switchPositionId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "switchPositionId")
    def position (implicit pos: SourceContext): Rep[Int] = field[Int](s, "position")
    def switchId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "switchId")
    def currentPosition (implicit pos: SourceContext): Rep[Int] = field[Int](s, "currentPosition")
  }

  implicit def switchSetMatchTypeToInfixOp (s: Rep[SwitchSetMatchType]) = SwitchSetMatchTypeInfixOp (s)
}

case class SwitchSetMatch(semaphoreId: Int, routeId: Int, switchPositionId: Int,
                          position: Int, switchId: Int, currentPosition: Int) extends SwitchSetMatchType {}

trait SwitchSetMatchInfixOps extends SwitchSetMatchTypeSchema {}

/*trait SwitchSetMatch {
  type SwitchSetMatchTuple = (Int, Int, Int, Int)

  def SwitchSetMatch(semaphoreId: Int, routeId: Int, switchPositionId: Int, switchId: Int) =
    (semaphoreId, routeId, switchPositionId, switchId)

  implicit def SwitchSetMatchToInfixOps(ssm: Rep[SwitchSetMatchTuple]) =
    SwitchSetMatchInfixOps(ssm)

  case class SwitchSetMatchInfixOps(ssm: Rep[SwitchSetMatchTuple]) {
    def semaphoreId (implicit pos: SourceContext): Rep[Int] = ssm._1
    def routeId (implicit pos: SourceContext): Rep[Int] = ssm._2
    def switchPositionId (implicit pos: SourceContext): Rep[Int] = ssm._3
    def switchId (implicit pos: SourceContext): Rep[Int] = ssm._4
  }
}*/
