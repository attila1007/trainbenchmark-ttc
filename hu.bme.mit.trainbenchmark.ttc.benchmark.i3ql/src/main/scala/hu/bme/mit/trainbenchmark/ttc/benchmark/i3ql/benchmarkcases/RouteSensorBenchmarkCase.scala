package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema._
import idb.operators.impl.{EquiJoinView, SelectionView}

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
class RouteSensorBenchmarkCase extends I3QLBenchmarkCase{

  val IR = idb.syntax.iql.IR
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

  val join1 = EquiJoinView[SwitchPosition, Route](
    switchPositions,
    routes,
    Seq(swp => swp.routeId),
    Seq(r => r.id),
    isSet = true
  )

  val join2 = EquiJoinView[(SwitchPosition, Route), Switch](
    join1,
    switches,
    Seq(swpr => swpr._1.switchId),
    Seq(sw => sw.id),
    isSet = true
  )

  val join3 = EquiJoinView[((SwitchPosition, Route), Switch), TrackElement](
    join2,
    trackElements,
    Seq(swprsw => swprsw._2.id),
    Seq(te => te.id),
    isSet = true
  )

  val join4 = EquiJoinView[(((SwitchPosition, Route), Switch), TrackElement),Sensor](
    join3,
    sensors,
    Seq(swprswte => swprswte._2.sensorId),
    Seq(s => s.id),
    isSet = true
  )

  val selection1 = SelectionView[((((SwitchPosition, Route), Switch), TrackElement),Sensor)](
    join4,
    s => s._2.routeId != s._1._1._1._2.id,
    isSet = true
  )

  val result = selection1.asMaterialized

  override protected def read(): Unit = {
    commonfileName = bc.getInstanceModelPath + "/railway-" + bc.getSize + "-"
    readRoute()
    readSwitchPosition()
    readSwitch()
    readTrackElement()
    readSensor()
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val rsm: RouteSensorMatch = o.asInstanceOf[RouteSensorMatch]
      val old = Sensor(rsm.sensorId,rsm.sensorRouteId)
      val _new = Sensor(rsm.sensorId,rsm.routeId)
      sensors.update(old,_new)
    }
  }

  override protected def check(): util.Collection[AnyRef] = {
    var list = new util.ArrayList[AnyRef]()
    for (i <- result){
       list.add(RouteSensorMatch(i._1._1._1._2.id,
                                 i._2.id,
                                 i._2.routeId,
                                 i._1._1._1._1.id,
                                 i._1._1._2.id ))
    }
    matches = list
    matches
  }
}
