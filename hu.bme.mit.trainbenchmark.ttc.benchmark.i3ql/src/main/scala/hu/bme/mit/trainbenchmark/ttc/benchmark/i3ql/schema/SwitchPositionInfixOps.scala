package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  * position:
  * 1 = LEFT
  * 2 = RIGHT
  * 3 = STRAIGHT
  * 4 = FAILURE
  */

trait SwitchPositionType {
  def id: Int
  def switchId: Int
  def routeId: Int
  def position: Int
}

trait SwitchPositionTypeSchema {
  val IR: StructExp

  import IR._

  case class SwitchPositionTypeInfixOp(s: Rep[SwitchPositionType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def switchId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "switchId")
    def routeId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "routeId")
    def position (implicit pos: SourceContext): Rep[Int] = field[Int](s, "switchId")
  }

  implicit def switchPositionTypeToInfixOp (s: Rep[SwitchPositionType]) = SwitchPositionTypeInfixOp (s)
}

case class SwitchPosition(id: Int, switchId: Int, routeId: Int, position: Int) extends SwitchPositionType{}

trait SwitchPositionInfixOps extends SwitchPositionTypeSchema {}

/*trait SwitchPositionInfixOps {

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
}*/
