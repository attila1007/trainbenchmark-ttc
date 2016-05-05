/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.ttc.benchmark.i3ql_java.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.transformation.EMFIncQuerySemaphoreNeighborTransformation;
import hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.util.SemaphoreNeighborQuerySpecification;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.localsearch.matcher.integration.LocalSearchBackend;
import org.eclipse.incquery.runtime.matchers.backend.QueryEvaluationHint;

import com.google.common.collect.Maps;

public class EMFIncQuerySemaphoreNeighbor extends EMFIncQueryBenchmarkCase<SemaphoreNeighborMatch> {

	@Override
	protected Collection<Object> getResultSet() throws IncQueryException {
		final Collection<Object> matches = new HashSet<>();
		for (final SemaphoreNeighborMatch match : getMatcher().getAllMatches()) {
			matches.add(match);
		}
		return matches;
	}

	@Override
	protected IncQueryMatcher<SemaphoreNeighborMatch> getMatcher() throws IncQueryException {
		if(eiqbc.isLocalSearch()){
			return engine.getMatcher(SemaphoreNeighborQuerySpecification.instance(), new QueryEvaluationHint(LocalSearchBackend.class, Maps.<String, Object>newHashMap()));			
		} else {
			return engine.getMatcher(SemaphoreNeighborQuerySpecification.instance());
		}
	}

	@Override
	protected void modify(final Collection<Object> matches) {
		final EMFIncQuerySemaphoreNeighborTransformation transformation = new EMFIncQuerySemaphoreNeighborTransformation();
		transformation.transform(matches);
	}

}
