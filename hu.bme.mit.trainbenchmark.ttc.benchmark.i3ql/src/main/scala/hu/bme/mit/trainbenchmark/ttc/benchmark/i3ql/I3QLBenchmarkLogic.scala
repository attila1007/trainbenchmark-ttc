package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql

import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig
import hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.benchmarkcases._
import hu.bme.mit.trainbenchmark.ttc.benchmark.scenarios.AbstractBenchmarkLogic
import hu.bme.mit.trainbenchmark.ttc.constants.QueryConstants

/**
  * Created by Ati on 2016.04.01..
  */
abstract class BaseBenchmarkLogic extends AbstractBenchmarkLogic {
  protected def _benchmarkConfig: BenchmarkConfig = benchmarkConfig
  protected def _benchmarkConfig_=(s: BenchmarkConfig) { benchmarkConfig = s }
}

class I3QLBenchmarkLogic(config: BenchmarkConfig) extends BaseBenchmarkLogic {

  _benchmarkConfig = config

  override def getBenchmarkCase(query: String) = query match {
    case QueryConstants.POSLENGTH => { new PosLengthBenchmarkCase }
    case QueryConstants.SWITCHSENSOR => { new SwitchSensorBenchmarkCase }
    case QueryConstants.SWITCHSET => { new SwitchSetBenchmarkCase }
    case QueryConstants.ROUTESENSOR => { new RouteSensorBenchmarkCase }
    case _ => sys.error(s"Unknown query: $query")
  }


}