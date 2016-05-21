package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */

trait ConnectsToType {
  def fromId: Int
  def toId: Int
}

trait ConnectsToTypeSchema {
  val IR: StructExp

  import IR._

  case class ConnectsToTypeInfixOp(s: Rep[ConnectsToType])  {
    def fromId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "fromId")
    def toId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "toId")
  }

  implicit def connectsToTypeToInfixOp (s: Rep[ConnectsToType]) = ConnectsToTypeInfixOp (s)
}

case class ConnectsTo(fromId: Int, toId: Int) extends ConnectsToType{}

trait ConnectsToInfixOps extends ConnectsToTypeSchema {}


/*trait ConnectsToInfixOps {

  type ConnectsToTuple = (Int, Int)

  def ConnectsTo(fromId: Int, toId: Int) = (fromId, toId)

  implicit def connectsToInfixOps(ct: Rep[ConnectsToTuple]) =
    ConnectsToInfixOps(ct)

  case class ConnectsToInfixOps(ct: Rep[ConnectsToTuple]){
    def fromId (implicit pos: SourceContext) : Rep[Int] = ct._1
    def toId (implicit pos: SourceContext) : Rep[Int] = ct._2
  }
}*/
