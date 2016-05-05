package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import idb.syntax.iql._

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
class RouteSensorBenchmarkCase extends I3QLBenchmarkCase{
  /** EXT TASK 1: RouteSensor
    * description: All sensors that are associated with a switch that belongs to a route must also be associated
    * directly with the same route.
    * query: The query looks for sensors that are connected to a switch, but the sensor and the switch are
    * not connected to the same route.
    * match: < route;sensor;swP;sw >
    * repairTransformation: For a given match, the missing definedBy edge is inserted by connecting the
    * route in the match to the sensor.
    * goal: This pattern checks for the absence of circles, so the efficiency of the join and the antijoin
    * operations is tested.
    */

  val q = (SELECT (*) FROM (routes, switchPositions, switches, trackElements, sensors))
  /*  WHERE ( (r, swP, sw, te, s) => ((swP.routeFId == r.routeId) && (swP.switchId == sw.switchId) &&
    (te.trackElementId == sw.switchId) && (te.sensorFId == s.sensorId) && (s.routeF1Id != r.routeId) )))*/

  val result = q.asMaterialized

  override protected def read(): Unit = {
    readRoute()
    readSwitchPosition()
    readSwitch()
    readTrackElement()
    readSensor()
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val te: RouteSensorMatchTuple = o.asInstanceOf[RouteSensorMatchTuple]

      //segments.update(te,newTrackElement)
    }
  }

  override protected def check(): util.Collection[AnyRef] = {
    var list = new util.ArrayList[AnyRef]()
    for (i <- result){
      // if (i._2 < 0) { // hack TODO QUERY MŰKÖDJÖN (newm kell az if)
      // list.add(TrackElement(i._1, i._2))
      // }
    }
    matches = list
    matches
  }
}
