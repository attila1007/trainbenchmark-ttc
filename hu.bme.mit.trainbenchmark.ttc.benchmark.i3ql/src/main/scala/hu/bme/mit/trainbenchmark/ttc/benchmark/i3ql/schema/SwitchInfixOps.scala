package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.03..
  */
trait SwitchInfixOps {

  type SwitchTuple = (Int, Int)

  def Switch(switchId: Int, currentPosition: Int) = (switchId, currentPosition)

  implicit def switchToInfixOps(s: Rep[SwitchTuple]) =
    SwitchInfixOps(s)

  case class SwitchInfixOps(s: Rep[SwitchTuple]){
    def switchId (implicit pos: SourceContext) : Rep[Int] = s._1
    def currentPosition (implicit pos: SourceContext) : Rep[Int] = s._2
  }
}
