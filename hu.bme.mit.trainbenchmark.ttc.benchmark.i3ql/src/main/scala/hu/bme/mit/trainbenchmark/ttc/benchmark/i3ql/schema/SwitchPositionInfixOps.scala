package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.03..
  */
trait SwitchPositionInfixOps {

  type SwitchPositionTuple = (Int, Int, Int, Int)

  def SwitchPosition(switchPositionId: Int, switchId: Int, routeFId: Int, position: Int) =
    (switchPositionId, switchId, routeFId, position)

  implicit def switchPositionToInfixOps(s: Rep[SwitchPositionTuple]) =
    SwitchPositionInfixOps(s)

  case class SwitchPositionInfixOps(s: Rep[SwitchPositionTuple]){
    def switchPositionId (implicit pos: SourceContext): Rep[Int] = s._1
    def switchId (implicit pos: SourceContext): Rep[Int] = s._2
    def routeFId (implicit pos: SourceContext): Rep[Int] = s._3
    def position (implicit pos: SourceContext): Rep[Int] = s._4
  }
}
