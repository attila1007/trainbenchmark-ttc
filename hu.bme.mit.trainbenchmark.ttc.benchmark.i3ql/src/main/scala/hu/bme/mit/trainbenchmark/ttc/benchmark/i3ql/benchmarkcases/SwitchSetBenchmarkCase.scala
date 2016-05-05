package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import idb.syntax.iql._

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
class SwitchSetBenchmarkCase extends I3QLBenchmarkCase {
  /** TASK 3: SwitchSet
    * description: The entry semaphore of a route may only show GO if all switches along the route are in
    * the position prescribed by the route
    * query: The query checks for routes which have a semaphore that show the GO signal. Additionally,
    * the route follows a switch position (swP) that is connected to a switch (sw), but the switch position
    * (swP.position) defines a different position from the current position of the switch (sw.currentPosition).
    * match: < semaphore; route;swP;sw >
    * repairTransformation: For a given match, the currentPosition attribute of the switch is set to the
    * position of the switchPsosition.
    * goal: This pattern tests the efficiency of the join and filtering operations.
    */

  val q = (SELECT ( * ) FROM (semaphores, routes/*, switchPositions, switches*/))
   /* WHERE ( (sem, r, swP, sw) => ((sem.signal == 3) && (swP.routeFId == r.routeId) && (sem.semaphoreId == r.entrySemaphore) &&
    (swP.switchId == sw.switchId) && (swP.position != sw.currentPosition) )))*/

  val result = q.asMaterialized

  override protected def read(): Unit = {
      readSemaphore()
      readRoute()
      readSwitchPosition()
      readSwitch()
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val te: SwitchSetMatchTuple = o.asInstanceOf[SwitchSetMatchTuple]

      //segments.update(te,newTrackElement)
    }
  }

  override protected def check(): util.Collection[AnyRef] = {
    var list = new util.ArrayList[AnyRef]()
    for (i <- result){
     // if (i._2 < 0) { // hack TODO QUERY MŰKÖDJÖN (newm kell az if)
       // list.add(SwitchSetMatch(i._1, i._2, i._3, i._4))
     // }
    }
    matches = list
    matches
  }
}
