package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql;

/**
 * Created by Ati on 2016.04.01..
 */

import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.ttc.benchmark.config.ChangeSet;

import org.apache.commons.cli.ParseException;

public class I3QLBenchmarkConfig extends BenchmarkConfig {

    public I3QLBenchmarkConfig(final String[] args) throws ParseException {
        super(args);
    }

    public I3QLBenchmarkConfig(final int size, final int runs, final String query, final int iterationCount,
                               final ChangeSet changeSet, final long transformationConstant) {
        super(size, runs, query, iterationCount, changeSet, transformationConstant);
    }

    @Override
    public String getTool() {
        return "i3ql";

    }
}