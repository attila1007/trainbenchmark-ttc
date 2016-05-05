package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql

import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.schema._
import idb.SetTable
import java.util.Comparator
import scala.io.Source
import scala.language.implicitConversions
import hu.bme.mit.trainbenchmark.ttc.benchmark.benchmarkcases.AbstractBenchmarkCase

/**
  * Created by Ati on 2016.04.01..
  */

abstract class I3QLBenchmarkCase extends AbstractBenchmarkCase with InfixOps {

  var fileEnd = ".csv";
  var commonfileName = "../models/railway-1-"//bc.getInstanceModelPath + "/railway-" + bc.getSize + "-"

  val connectsTo =  SetTable.empty[ConnectsToTuple]
  val routes = SetTable.empty[RouteTuple]
  val segments = SetTable.empty[SegmentTuple]
  val semaphores = SetTable.empty[SemaphoreTuple]
  val sensors = SetTable.empty[SensorTuple]
  val switches = SetTable.empty[SwitchTuple]
  val switchPositions = SetTable.empty[SwitchPositionTuple]
  val trackElements = SetTable.empty[TrackElementTuple]

  override protected def registerComparator(): Unit = {
   comparator = I3QLBenchmarkComparator
 }

  object I3QLBenchmarkComparator extends Comparator[Object]{
    def compare(o1: AnyRef, o2: AnyRef): Int = (o1, o2) match {
      /* case (p1: Product, p2: Product) =>
         if (p1.productArity != p2.productArity)
           p1.productArity - p2.productArity
         else {
           ((p1.productIterator zip p2.productIterator) collect {
             case (x: RailwayElement, y: RailwayElement) => x.getId - y.getId
           }).sum
         }*/
      case (x: SegmentTuple, y: SegmentTuple) => x._1 - y._1
      case (x: ConnectsToTuple, y: ConnectsToTuple) => {
        if (x._1 == y._1 ) { x._2 - y._2}
        else {x._1 - y._1}
      }
      case (x: RouteTuple, y: RouteTuple) => x._1 - y._1
      case (x: SemaphoreTuple, y: SemaphoreTuple) => x._1 - y._1
      case (x: SensorTuple, y: SensorTuple) => x._1 - y._1
      case (x: SwitchTuple, y: SwitchTuple) => x._1 - y._1
      case (x: SwitchPositionTuple, y: SwitchPositionTuple) => x._1 - y._1
      case (x: TrackElementTuple, y: TrackElementTuple) => x._1 - y._1
      case _ => sys.error(s"Unknown types to compare o1=`$o1` o2=`$o2`")
    }
  }

  def readConnectsTo(): Unit = {
    var filename = commonfileName + "connectsTo" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      connectsTo += ConnectsTo(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readRoute(): Unit = {
    var filename = commonfileName + "routes" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      routes += Route(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)),java.lang.Integer.parseInt(values(2)))
    }
  }

  def readSegment(): Unit = {
    var filename = commonfileName + "segments" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      segments += Segment(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSemaphore(): Unit = {
    var filename = commonfileName + "semaphores" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      semaphores += Semaphore(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSensor(): Unit = {
    var filename = commonfileName + "sensors" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      sensors += Sensor(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSwitch(): Unit = {
    var filename = commonfileName + "switches" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      switches += Switch(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }

  def readSwitchPosition(): Unit = {
    var filename = commonfileName + "switchPositions" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      switchPositions += SwitchPosition(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)),java.lang.Integer.parseInt(values(2)),java.lang.Integer.parseInt(values(3)))
    }
  }

  def readTrackElement(): Unit = {
    var filename = commonfileName + "trackElements" + fileEnd
    for(line <- Source.fromFile(filename).getLines()){
      val values = line.split(";")
      trackElements += TrackElement(java.lang.Integer.parseInt(values(0)),java.lang.Integer.parseInt(values(1)))
    }
  }
}