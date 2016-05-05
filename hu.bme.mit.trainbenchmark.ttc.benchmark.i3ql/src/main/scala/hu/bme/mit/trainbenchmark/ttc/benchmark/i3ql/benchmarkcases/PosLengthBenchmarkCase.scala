package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema.{SegmentInfixOps, InfixOps}
import idb.syntax.iql._

import scala.language.implicitConversions




/**
  * Created by Ati on 2016.04.26..
  */

class PosLengthBenchmarkCase extends I3QLBenchmarkCase with SegmentInfixOps {

  /** TASK 1: PosLength
    * description: Every segment must have a positive length.
    * query: The query checks for segments with a length less than or equal to zero
    * match: segment
    * repairTransformation: The length attribute of the segment in the match is updated to 􀀀length+1.
    * goal: This query defines an attribute check. This is a common use case in validation scenarios.
    */

  val q = SELECT (*) FROM (segments)// WHERE (s => (s.length < 0))
  val result = q.asMaterialized

  override protected def check(): java.util.Collection[AnyRef] = {

    //val q = (SELECT (*) FROM (segments) //WHERE ((s : Rep[Segment]) => (field[Int](s, "length") < 0)))
   // val q = SELECT (*) FROM (segments) //WHERE (s => (s.length < 0))
    //val result = q.asMaterialized

   /* var sList = new ListBuffer[AnyRef]()
    for (i <- result){
      sList += Segment(i._1,i._2)
     /* var id = field[Int](i, "id")
      var lenght = field[Int](i, "length")
      sList += Segment(id,lenght)*/
    }
    matches = sList
    matches*/
   var list = new util.ArrayList[AnyRef]()
    for (i <- result){
      if (i._2 <= 0) { // hack TODO QUERY MŰKÖDJÖN alapból nem kellene az if csak hozzá kell adni
        list.add(Segment(i._1, i._2))
      }
    }
    matches = list
    matches
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
   /* import scala.collection.JavaConversions._
    for (m <- matches) {
      var plm: SegmentTuple = m.asInstanceOf[SegmentTuple]
      val length: Int = plm._2
      //plm = Segment(plm._1,-length + 1)
      var plm_new = Segment(plm._1,-length + 1)
      segments.update(plm,plm_new)
      //plm = Segment(plm._1,-length + 1)
    }*/
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val s: SegmentTuple = o.asInstanceOf[SegmentTuple]
      val lenght: Int = s._2
      segments.update(s,Segment(s._1,(-lenght +1)))
    }
  }

  override protected def read(): Unit = {
    readSegment()
  }

}
