package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

/**
  * Created by Ati on 2016.05.03..
  */
trait RouteInfixOps {

  type RouteTuple = (Int, Int, Int)

  def Route(routeId: Int, entrySemaphore: Int, exitSemaphore: Int) =
    (routeId, entrySemaphore, exitSemaphore)

  implicit def routeToInfixOps(r: Rep[RouteTuple]) =
    RouteInfixOps(r)

  case class RouteInfixOps(r: Rep[RouteTuple]) {
    def routeId (implicit pos: SourceContext): Rep[Int] = r._1
    def entrySemaphore (implicit pos: SourceContext): Rep[Int] = r._2
    def exitSemaphore (implicit pos: SourceContext): Rep[Int] = r._3
  }
}
