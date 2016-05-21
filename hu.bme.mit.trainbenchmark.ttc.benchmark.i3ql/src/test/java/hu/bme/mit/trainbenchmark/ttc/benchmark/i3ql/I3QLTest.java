package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql;

import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.ttc.benchmark.config.ChangeSet;
import hu.bme.mit.trainbenchmark.ttc.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.ttc.benchmark.test.TrainBenchmarkTest;

import java.io.IOException;

/**
 * Created by Ati on 2016.04.01..
 */
public class I3QLTest extends TrainBenchmarkTest {

    @Override
    public BenchmarkConfig initialize(final String query) throws IOException {
        return new I3QLBenchmarkConfig(256, 1, query, 1, ChangeSet.FIXED, 1);
    }

    @Override
    protected AbstractBenchmarkLogic getBenchmarkLogic(final BenchmarkConfig bc) {
        return new I3QLBenchmarkLogic(bc);
    }
}
