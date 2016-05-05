package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases

import java.util

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.I3QLBenchmarkCase
import idb.syntax.iql._

import scala.language.implicitConversions

/**
  * Created by Ati on 2016.05.03..
  */
class SwitchSensorBenchmarkCase extends I3QLBenchmarkCase {

  /** TASK 2: SwitchSensor
    * description: Every switch must have at least one sensor connected to it.
    * query: The query checks for switches that have no sensors associated with them.
    * match: sw
    * repairTransformation: For a given match, a sensor is created and connected to the switch.
    * goal: This query checks whether an object is connected to a relation. This pattern is common in more
    * complex queries, e.g. it is used in the RouteSensor and the SemaphoreNeighbor queries.
    */

  val q = (SELECT (*) FROM (trackElements)) //WHERE ( sw => (sw.sensorFId == -1) ))
  val result = q.asMaterialized

  override protected def read(): Unit = {
    readTrackElement()
    readSensor()
    readRoute()
  }

  override protected def modify(matches: util.Collection[AnyRef]): Unit = {
    import scala.collection.JavaConversions._
    for (o <- matches) {
      val te: TrackElementTuple = o.asInstanceOf[TrackElementTuple]
      val r = scala.util.Random

      val newSensor = Sensor(r.nextInt(1000)+5000,-1) //TODO HOGYAN CSINÁLUNK ÚJ SENSORT
      sensors += newSensor
      val newTrackElement = TrackElement(te._1,newSensor._1)
      segments.update(te,newTrackElement)
    }
  }

  override protected def check(): util.Collection[AnyRef] = {
    var list = new util.ArrayList[AnyRef]()
    for (i <- result){
      if (i._2 < 0) { // hack TODO QUERY MŰKÖDJÖN (newm kell az if)
        list.add(TrackElement(i._1, i._2))
      }
    }
    matches = list
    matches
  }
}
