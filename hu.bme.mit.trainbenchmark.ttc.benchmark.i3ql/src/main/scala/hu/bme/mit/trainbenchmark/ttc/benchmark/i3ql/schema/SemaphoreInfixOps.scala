package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

/**
  * Created by Ati on 2016.05.03..
  */
import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext

trait SemaphoreInfixOps {

  type SemaphoreTuple = (Int, Int)

  def Semaphore(semaphoreId: Int, signal: Int) = (semaphoreId, signal)

  implicit def semaphoreToInfixOps(s: Rep[SemaphoreTuple]) =
    SemaphoreInfixOps(s)

  case class SemaphoreInfixOps(s: Rep[SemaphoreTuple]) {
    def semaphoreId (implicit pos: SourceContext) : Rep[Int] = s._1
    def signal (implicit pos: SourceContext) : Rep[Int] = s._2
  }

}
