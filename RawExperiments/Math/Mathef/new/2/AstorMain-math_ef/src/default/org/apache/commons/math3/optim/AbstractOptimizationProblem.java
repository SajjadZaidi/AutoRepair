package org.apache.commons.math3.optim;


public abstract class AbstractOptimizationProblem<PAIR> implements org.apache.commons.math3.optim.OptimizationProblem<PAIR> {
	private static final org.apache.commons.math3.optim.AbstractOptimizationProblem.MaxEvalCallback MAX_EVAL_CALLBACK = new org.apache.commons.math3.optim.AbstractOptimizationProblem.MaxEvalCallback();

	private static final org.apache.commons.math3.optim.AbstractOptimizationProblem.MaxIterCallback MAX_ITER_CALLBACK = new org.apache.commons.math3.optim.AbstractOptimizationProblem.MaxIterCallback();

	private final int maxEvaluations;

	private final int maxIterations;

	private final org.apache.commons.math3.optim.ConvergenceChecker<PAIR> checker;

	protected AbstractOptimizationProblem(final int maxEvaluations ,final int maxIterations ,final org.apache.commons.math3.optim.ConvergenceChecker<PAIR> checker) {
		this.maxEvaluations = maxEvaluations;
		this.maxIterations = maxIterations;
		this.checker = checker;
	}

	public org.apache.commons.math3.util.Incrementor getEvaluationCounter() {
		return new org.apache.commons.math3.util.Incrementor(org.apache.commons.math3.optim.AbstractOptimizationProblem.this.maxEvaluations , org.apache.commons.math3.optim.AbstractOptimizationProblem.MAX_EVAL_CALLBACK);
	}

	public org.apache.commons.math3.util.Incrementor getIterationCounter() {
		return new org.apache.commons.math3.util.Incrementor(org.apache.commons.math3.optim.AbstractOptimizationProblem.this.maxIterations , org.apache.commons.math3.optim.AbstractOptimizationProblem.MAX_ITER_CALLBACK);
	}

	public org.apache.commons.math3.optim.ConvergenceChecker<PAIR> getConvergenceChecker() {
		return checker;
	}

	private static class MaxEvalCallback implements org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback {
		public void trigger(int max) {
			throw new org.apache.commons.math3.exception.TooManyEvaluationsException(max);
		}
	}

	private static class MaxIterCallback implements org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback {
		public void trigger(int max) {
			throw new org.apache.commons.math3.exception.TooManyIterationsException(max);
		}
	}
}

