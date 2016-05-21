package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

/**
  * Created by Ati on 2016.05.03..
  * Signal:
  * 1 = GO
  * 2 = STOP
  * 3 = FAILURE
  */
import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

trait SemaphoreType {
  def id: Int
  def signal: Int
}

trait SemaphoreTypeSchema {
  val IR: StructExp

  import IR._

  case class SemaphoreTypeInfixOp(s: Rep[SemaphoreType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def signal (implicit pos: SourceContext): Rep[Int] = field[Int](s, "signal")
  }

  implicit def semaphoreTypeToInfixOp (s: Rep[SemaphoreType]) = SemaphoreTypeInfixOp (s)
}

case class Semaphore(id: Int, signal: Int) extends SemaphoreType{}

trait SemaphoreInfixOps extends SemaphoreTypeSchema {}


/*trait SemaphoreInfixOps {

  type SemaphoreTuple = (Int, Int)

  def Semaphore(semaphoreId: Int, signal: Int) = (semaphoreId, signal)

  implicit def semaphoreToInfixOps(s: Rep[SemaphoreTuple]) =
    SemaphoreInfixOps(s)

  case class SemaphoreInfixOps(s: Rep[SemaphoreTuple]) {
    def semaphoreId (implicit pos: SourceContext) : Rep[Int] = s._1
    def signal (implicit pos: SourceContext) : Rep[Int] = s._2
  }
}*/
