package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */

trait RouteType {
  def id: Int
  def entrySemaphore: Int
  def exitSemaphore: Int
}

trait RouteTypeSchema {
  val IR: StructExp

  import IR._

  case class RouteTypeInfixOp(s: Rep[RouteType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def entrySemaphore (implicit pos: SourceContext): Rep[Int] = field[Int](s, "entrySemaphore")
    def exitSemaphore (implicit pos: SourceContext): Rep[Int] = field[Int](s, "exitSemaphore")
  }

  implicit def routeTypeToInfixOp (s: Rep[RouteType]) = RouteTypeInfixOp (s)
}

case class Route(id: Int, entrySemaphore: Int, exitSemaphore: Int) extends RouteType{}

trait RouteInfixOps extends RouteTypeSchema {}

/*trait RouteInfixOps {

  type RouteTuple = (Int, Int, Int)

  def Route(entrySemaphore: Int, entrySemaphore: Int, exitSemaphore: Int) =
    (entrySemaphore, entrySemaphore, exitSemaphore)

  implicit def routeToInfixOps(r: Rep[RouteTuple]) =
    RouteInfixOps(r)

  case class RouteInfixOps(r: Rep[RouteTuple]) {
    def entrySemaphore (implicit pos: SourceContext): Rep[Int] = r._1
    def entrySemaphore (implicit pos: SourceContext): Rep[Int] = r._2
    def exitSemaphore (implicit pos: SourceContext): Rep[Int] = r._3
  }
}*/
