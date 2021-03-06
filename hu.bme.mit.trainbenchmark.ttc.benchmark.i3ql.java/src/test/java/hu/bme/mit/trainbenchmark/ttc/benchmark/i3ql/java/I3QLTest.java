package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql.java;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.ttc.benchmark.config.ChangeSet;
import hu.bme.mit.trainbenchmark.ttc.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.ttc.benchmark.test.TrainBenchmarkTest;


/**
 * Created by Ati on 2016.04.01..
 */
public class I3QLTest extends TrainBenchmarkTest {

    @Override
    public BenchmarkConfig initialize(String query) throws IOException {
        return new I3QLBenchmarkConfig(1, 1, query, 1, ChangeSet.FIXED, 1);
    }

    @Override
    protected AbstractBenchmarkLogic getBenchmarkLogic(BenchmarkConfig bc) {
        return new I3QLBenchmarkLogic(bc);
    }
}
