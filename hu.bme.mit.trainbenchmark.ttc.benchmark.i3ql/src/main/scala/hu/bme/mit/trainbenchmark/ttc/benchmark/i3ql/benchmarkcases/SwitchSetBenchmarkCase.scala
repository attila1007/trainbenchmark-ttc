package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema._
import idb.operators.impl.{EquiJoinView, SelectionView}

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
class SwitchSetBenchmarkCase extends I3QLBenchmarkCase {

  val IR = idb.syntax.iql.IR
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

  val selection1 = SelectionView[Semaphore](
    semaphores,
    s => s.signal == 1,
    isSet = true
  )

  val join1 = EquiJoinView[Semaphore, Route](
    selection1,
    routes,
    Seq(s => s.id),
    Seq(r => r.entrySemaphore),
    isSet = true
  )

  val join2 = EquiJoinView[(Semaphore, Route), SwitchPosition](
    join1,
    switchPositions,
    Seq(fp => fp._2.id),
    Seq(swp => swp.routeId),
    isSet = true
  )

  val join3 = EquiJoinView[((Semaphore, Route), SwitchPosition), Switch](
    join2,
    switches,
    Seq(srswp => srswp._2.switchId),
    Seq(sw => sw.id),
    isSet = true
  )

  val selection2 = SelectionView[(((Semaphore, Route), SwitchPosition), Switch)](
    join3,
    s => s._1._2.position != s._2.currentPosition,
    isSet = true
  )

  val result = selection2.asMaterialized

  override protected def read(): Unit = {
      commonfileName = bc.getInstanceModelPath + "/railway-" + bc.getSize + "-"
      readSemaphore()
      readRoute()
      readSwitchPosition()
      readSwitch()
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val ssm: SwitchSetMatch = o.asInstanceOf[SwitchSetMatch]
      val old = Switch(ssm.switchId, ssm.currentPosition)
      val _new = Switch(ssm.switchId, ssm.position);
      switches.update(old,_new)
    }
  }

  override protected def check(): util.Collection[AnyRef] = {
    var list = new util.ArrayList[AnyRef]()
    for (i <- result){
      list.add(new SwitchSetMatch(i._1._1._1.id,
                                  i._1._1._2.id,
                                  i._1._2.id,
                                  i._1._2.position,
                                  i._2.id,
                                  i._2.currentPosition))
    }
    matches = list
    matches
  }
}
