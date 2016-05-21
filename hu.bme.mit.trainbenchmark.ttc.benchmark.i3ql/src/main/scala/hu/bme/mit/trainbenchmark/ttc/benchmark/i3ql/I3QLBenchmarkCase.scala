package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql

import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema._
import hu.bme.mit.trainbenchmark.ttc.benchmark.scenarios.AbstractBenchmarkLogic
import idb.SetTable
import java.util.Comparator
import scala.io.Source
import scala.language.implicitConversions
import hu.bme.mit.trainbenchmark.ttc.benchmark.benchmarkcases.AbstractBenchmarkCase

/**
  * Created by Ati on 2016.04.01..
  */


abstract class I3QLBenchmarkCase extends AbstractBenchmarkCase with InfixOps {

  var idx = 0
  var fileEnd = ".csv"
  var commonfileName = "../models/railway-1-"//bc.getInstanceModelPath + "/railway-" + bc.getSize + "-"

  val connectsTo =  SetTable.empty[ConnectsTo]
  val routes = SetTable.empty[Route]
  val segments = SetTable.empty[Segment]
  val semaphores = SetTable.empty[Semaphore]
  val sensors = SetTable.empty[Sensor]
  val switches = SetTable.empty[Switch]
  val switchPositions = SetTable.empty[SwitchPosition]
  val trackElements = SetTable.empty[TrackElement]

  override protected def registerComparator(): Unit = {
   comparator = I3QLBenchmarkComparator
 }

  object I3QLBenchmarkComparator extends Comparator[Object]{
    def compare(o1: AnyRef, o2: AnyRef): Int = (o1, o2) match {
      case (x: Segment, y: Segment) => x.id - y.id
      case (x: ConnectsTo, y: ConnectsTo) => {
        if (x.fromId == y.fromId ) { x.toId - y.toId}
        else {x.fromId - y.fromId}
      }
      case (x: Route, y: Route) => x.id - y.id
      case (x: Semaphore, y: Semaphore) => x.id - y.id
      case (x: Sensor, y: Sensor) => x.id - y.id
      case (x: Switch, y: Switch) => x.id - y.id
      case (x: SwitchPosition, y: SwitchPosition) => x.id - y.id
      case (x: TrackElement, y: TrackElement) => x.id - y.id
      case (x: SwitchSetMatch, y: SwitchSetMatch) =>  {
        if (x.switchId == y.switchId ) {
          if (x.switchPositionId == y.switchPositionId) {
            x.routeId - y.routeId
          } else {
            x.switchPositionId - y.switchPositionId
          }
        }
        else {x.switchId - y.switchId}
      }
      case (x: RouteSensorMatch, y: RouteSensorMatch) =>  {
        if (x.sensorId == y.sensorId ) {
            x.routeId - y.routeId
        }
        else {x.sensorId - y.sensorId}
      }
      case _ => sys.error(s"Unknown types to compare o1=`$o1` o2=`$o2`")
    }
  }

  def readConnectsTo(): Unit = {
    var filename = commonfileName + "connectsTo" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      connectsTo += ConnectsTo(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readRoute(): Unit = {
    var filename = commonfileName + "routes" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      routes += Route(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)),java.lang.Integer.parseInt(values(2)))
    }
  }

  def readSegment(): Unit = {
    var filename = commonfileName + "segments" + fileEnd
    println("reading " + filename)
    var i = 0
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      var a = java.lang.Integer.parseInt(values(0))
      var b = java.lang.Integer.parseInt(values(1))
      //println(a + ", " + b)
      segments += new Segment(a,b)
    }
    segments.foreach(println(_))
  }

  def readSemaphore(): Unit = {
    var filename = commonfileName + "semaphores" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      semaphores += Semaphore(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSensor(): Unit = {
    var filename = commonfileName + "sensors" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      sensors += Sensor(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSwitch(): Unit = {
    var filename = commonfileName + "switches" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      switches += Switch(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSwitchPosition(): Unit = {
    var filename = commonfileName + "switchPositions" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      switchPositions += SwitchPosition(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)),java.lang.Integer.parseInt(values(2)),java.lang.Integer.parseInt(values(3)))
    }
  }

  def readTrackElement(): Unit = {
    var filename = commonfileName + "trackElements" + fileEnd
    println("reading " + filename)
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      trackElements += TrackElement(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }
}