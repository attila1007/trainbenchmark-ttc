package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema.{Sensor, TrackElement}
import idb.operators.impl.SelectionView

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
class SwitchSensorBenchmarkCase extends I3QLBenchmarkCase {

  val IR = idb.syntax.iql.IR

  /** TASK 2: SwitchSensor
    * description: Every switch must have at least one sensor connected to it.
    * query: The query checks for switches that have no sensors associated with them.
    * match: sw
    * repairTransformation: For a given match, a sensor is created and connected to the switch.
    * goal: This query checks whether an object is connected to a relation. This pattern is common in more
    * complex queries, e.g. it is used in the RouteSensor and the SemaphoreNeighbor queries.
    */

  val selection1 = SelectionView[TrackElement](
    trackElements,
    t => t.sensorId == -1,
    isSet = true
  )

  val result = selection1.asMaterialized

  override protected def read(): Unit = {
    commonfileName = bc.getInstanceModelPath + "/railway-" + bc.getSize + "-"
    readTrackElement()
    readSensor()
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val te: TrackElement = o.asInstanceOf[TrackElement]

      val i = Int.MaxValue - idx;
      idx = idx + 1

      val newSensor = Sensor(i,-1)
      sensors += newSensor
      val newTrackElement = TrackElement(te.id,newSensor.id)

      trackElements.update(te,newTrackElement)
    }
  }

  override protected def check(): util.Collection[AnyRef] = {
    var list = new util.ArrayList[AnyRef]()
    for (i <- result){
        list.add(TrackElement(i.id, i.sensorId))
    }
    matches = list
    matches
  }
}
