package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.03..
  */
trait ConnectsToInfixOps {

  type ConnectsToTuple = (Int, Int)

  def ConnectsTo(fromId: Int, toId: Int) = (fromId, toId)

  implicit def connectsToInfixOps(ct: Rep[ConnectsToTuple]) =
    ConnectsToInfixOps(ct)

  case class ConnectsToInfixOps(ct: Rep[ConnectsToTuple]){
    def fromId (implicit pos: SourceContext) : Rep[Int] = ct._1
    def toId (implicit pos: SourceContext) : Rep[Int] = ct._2
  }

}
