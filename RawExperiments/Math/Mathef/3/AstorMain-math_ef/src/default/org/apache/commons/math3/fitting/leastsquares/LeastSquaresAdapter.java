package org.apache.commons.math3.fitting.leastsquares;


public class LeastSquaresAdapter implements org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem {
	private final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem problem;

	public LeastSquaresAdapter(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem problem) {
		this.problem = problem;
	}

	public org.apache.commons.math3.linear.RealVector getStart() {
		return problem.getStart();
	}

	public int getObservationSize() {
		return problem.getObservationSize();
	}

	public int getParameterSize() {
		return problem.getParameterSize();
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation evaluate(final org.apache.commons.math3.linear.RealVector point) {
		return problem.evaluate(point);
	}

	public org.apache.commons.math3.util.Incrementor getEvaluationCounter() {
		return problem.getEvaluationCounter();
	}

	public org.apache.commons.math3.util.Incrementor getIterationCounter() {
		return problem.getIterationCounter();
	}

	public org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> getConvergenceChecker() {
		return problem.getConvergenceChecker();
	}
}

