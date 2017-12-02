package org.apache.commons.math3.optim;


public interface OptimizationProblem<PAIR> {
	org.apache.commons.math3.util.Incrementor getEvaluationCounter();

	org.apache.commons.math3.util.Incrementor getIterationCounter();

	org.apache.commons.math3.optim.ConvergenceChecker<PAIR> getConvergenceChecker();
}

