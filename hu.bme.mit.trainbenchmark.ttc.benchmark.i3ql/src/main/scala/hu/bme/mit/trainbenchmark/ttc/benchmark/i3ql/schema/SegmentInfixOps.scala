package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema

/**
  * Created by Ati on 2016.04.12..
  */
import scala.language.implicitConversions
import idb.syntax.iql.IR._
import scala.reflect.SourceContext


trait SegmentInfixOps {

  type SegmentTuple = (Int, Int)

  def Segment(segmentId: Int, length: Int)  = (segmentId, length)

  implicit def segmentToInfixOps(s: Rep[SegmentTuple]) =
    SegmentInfixOps(s)

  case class SegmentInfixOps(s: Rep[SegmentTuple]) {
    def segmentId  (implicit pos: SourceContext)  : Rep[Int] = s._1
    def length  (implicit pos: SourceContext) : Rep[Int] = s._2
  }
}

/*trait SegmentInfixOps {

  implicit def segmentToInfixOps(t: Rep[Segment]) =
    SegmentInfixOps(t)

  case class SegmentInfixOps(s: Rep[Segment]) {

    def id (implicit pos: SourceContext) : Rep[Int] = field[Int](s, "id")

    def lenght (implicit pos: SourceContext) : Rep[Int] = field[Int](s, "length")
  }
}*/