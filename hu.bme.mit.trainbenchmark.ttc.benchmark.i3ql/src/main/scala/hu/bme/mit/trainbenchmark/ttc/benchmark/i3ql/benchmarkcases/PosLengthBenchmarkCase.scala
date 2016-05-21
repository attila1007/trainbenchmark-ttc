package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import idb.operators.impl.SelectionView
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema.{Segment}

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.04.26..
  */

class PosLengthBenchmarkCase extends I3QLBenchmarkCase {

  val IR = idb.syntax.iql.IR
  /** TASK 1: PosLength
    * description: Every segment must have a positive length.
    * query: The query checks for segments with a length less than or equal to zero
    * match: segment
    * repairTransformation: The length attribute of the segment in the match is updated to 􀀀length+1.
    * goal: This query defines an attribute check. This is a common use case in validation scenarios.
    */

  val selection1 = SelectionView[Segment](
    segments,
    s => s.lenght < 0,
    isSet = true
  )

  val result = selection1.asMaterialized

  override protected def check(): java.util.Collection[AnyRef] = {

   var list = new util.ArrayList[AnyRef]()
    for (i <- result){
        list.add(Segment(i.id, i.lenght))

    }
    matches = list
    matches
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {

    import scala.collection.JavaConversions._
    for (o <- matches) {
      val s: Segment = o.asInstanceOf[Segment]
      val lenght: Int = s.lenght
      segments.update(s, Segment(s.id,(-lenght +1)))
    }
  }

  override protected def read(): Unit = {
    commonfileName = bc.getInstanceModelPath + "/railway-" + bc.getSize + "-"

    readSegment()
  }

}
