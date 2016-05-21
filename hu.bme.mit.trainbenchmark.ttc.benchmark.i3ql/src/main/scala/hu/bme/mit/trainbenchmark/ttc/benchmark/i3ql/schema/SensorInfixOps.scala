package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */


trait SensorType {
  def id: Int
  def routeId: Int
}

trait SensorTypeSchema {
  val IR: StructExp

  import IR._

  case class SensorTypeInfixOp(s: Rep[SensorType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def routeId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "routeId")
  }

  implicit def sensorTypeToInfixOp (s: Rep[SensorType]) = SensorTypeInfixOp (s)
}

case class Sensor(id: Int, routeId: Int) extends SensorType{}

trait SensorInfixOps extends SensorTypeSchema {}


/*
trait SensorInfixOps {

  type SensorTuple = (Int, Int)

  def Sensor(sensorId: Int, routeF1Id: Int) = (sensorId, routeF1Id)

  implicit def sensorToInfixOps(s: Rep[SensorTuple]) =
    SensorInfixOps(s)

  case class SensorInfixOps(s: Rep[SensorTuple]){
    def sensorId (implicit pos: SourceContext) : Rep[Int] = s._1
    def routeF1Id (implicit pos: SourceContext) : Rep[Int] = s._2
  }
}*/
