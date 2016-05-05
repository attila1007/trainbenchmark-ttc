package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.03..
  */
trait SensorInfixOps {

  type SensorTuple = (Int, Int)

  def Sensor(sensorId: Int, routeF1Id: Int) = (sensorId, routeF1Id)

  implicit def sensorToInfixOps(s: Rep[SensorTuple]) =
    SensorInfixOps(s)

  case class SensorInfixOps(s: Rep[SensorTuple]){
    def sensorId (implicit pos: SourceContext) : Rep[Int] = s._1
    def routeF1Id (implicit pos: SourceContext) : Rep[Int] = s._2
  }

}
