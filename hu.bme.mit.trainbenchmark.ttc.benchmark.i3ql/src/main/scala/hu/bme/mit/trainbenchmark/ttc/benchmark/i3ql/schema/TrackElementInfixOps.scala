package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
trait TrackElementType {
  def id: Int
  def sensorId: Int
}

trait TrackElementTypeSchema {
  val IR: StructExp

  import IR._

  case class TrackElementTypeInfixOp(s: Rep[TrackElementType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def sensorId (implicit pos: SourceContext): Rep[Int] = field[Int](s, "sensorId")
  }

  implicit def trackElementTypeToInfixOp (s: Rep[TrackElementType]) = TrackElementTypeInfixOp (s)
}

case class TrackElement(id: Int, sensorId: Int) extends TrackElementType {

}

trait TrackElementInfixOps extends TrackElementTypeSchema {

}


/*
trait TrackElementInfixOps {

  type TrackElementTuple = (Int, Int)

  def TrackElement(trackElementId: Int, sensorFId: Int) = (trackElementId, sensorFId)

  implicit def trackElementToInfixOps(te: Rep[TrackElementTuple])=
    TrackElementInfixOps(te)

  case class TrackElementInfixOps(te: Rep[TrackElementTuple]) {
    def trackElementId (implicit pos: SourceContext) : Rep[Int] = te._1
    def sensorFId (implicit pos: SourceContext) : Rep[Int] = te._2
  }
}*/
