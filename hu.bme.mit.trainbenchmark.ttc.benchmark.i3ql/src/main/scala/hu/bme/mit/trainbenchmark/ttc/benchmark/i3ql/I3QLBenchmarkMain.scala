package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql

import idb.SetTable
import idb.syntax.iql.IR._
import idb.syntax.iql._

/**
  * Created by Ati on 2016.04.01..
  */
object I3QLBenchmarkMain extends App {

  new I3QLBenchmarkLogic(new I3QLBenchmarkConfig(args)).runBenchmark()

}
