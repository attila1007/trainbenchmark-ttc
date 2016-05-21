package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */

trait SwitchType {
  def id: Int
  def currentPosition: Int
}

trait SwitchTypeSchema {
  val IR: StructExp

  import IR._

  case class SwitchTypeInfixOp(s: Rep[SwitchType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def currentPosition (implicit pos: SourceContext): Rep[Int] = field[Int](s, "currentPosition")
  }

  implicit def switchTypeToInfixOp (s: Rep[SwitchType]) = SwitchTypeInfixOp (s)
}

case class Switch(id: Int, currentPosition: Int) extends SwitchType{}

trait SwitchInfixOps extends SwitchTypeSchema {}

/*
trait SwitchInfixOps {

  type SwitchTuple = (Int, Int)

  def Switch(switchId: Int, currentPosition: Int) = (switchId, currentPosition)

  implicit def switchToInfixOps(s: Rep[SwitchTuple]) =
    SwitchInfixOps(s)

  case class SwitchInfixOps(s: Rep[SwitchTuple]){
    def switchId (implicit pos: SourceContext) : Rep[Int] = s._1
    def currentPosition (implicit pos: SourceContext) : Rep[Int] = s._2
  }
}*/
