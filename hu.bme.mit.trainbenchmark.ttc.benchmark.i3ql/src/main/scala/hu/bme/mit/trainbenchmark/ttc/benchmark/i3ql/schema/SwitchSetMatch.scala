package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._

import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.04..
  */
trait SwitchSetMatch {
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
}
