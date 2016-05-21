package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

import scala.reflect.SourceContext
import scala.virtualization.lms.common.StructExp
import scala.language.implicitConversions

/**
  * Created by Ati on 2016.04.12..
  */

trait SegmentType {
  def id: Int
  def lenght: Int
}

trait SegmentTypeSchema {
  val IR: StructExp

  import IR._

  case class SegmentTypeInfixOp(s: Rep[SegmentType])  {
    def id (implicit pos: SourceContext): Rep[Int] = field[Int](s, "id")
    def lenght (implicit pos: SourceContext): Rep[Int] = field[Int](s, "lenght")
  }

  implicit def segmentTypeToInfixOp (s: Rep[SegmentType]) = SegmentTypeInfixOp (s)
}

case class Segment(id: Int, lenght: Int) extends SegmentType{}

trait SegmentInfixOps extends SegmentTypeSchema {}

/*case class Segment (id : Int, lenght : Int)

trait SegmentInfixOps {
  val IR: StructExp
  import IR._

  def Segment (id : Rep[Int], lenght : Rep[Int]) =
    struct[Segment](
      ClassTag[Segment]("Segment"),
      Seq ("id" -> id, "lenght" -> lenght)
    )

  case class SegmentInfixOps (x: Rep[Segment])
  {
    def id (implicit pos: SourceContext): Rep[Int] = field(x, "id")
    def lenght (implicit pos: SourceContext): Rep[Int] = field(x, "lenght")
  }

  implicit def SegmentToInfixOps (x: Rep[Segment]) :SegmentInfixOps = SegmentInfixOps (x)

}*/