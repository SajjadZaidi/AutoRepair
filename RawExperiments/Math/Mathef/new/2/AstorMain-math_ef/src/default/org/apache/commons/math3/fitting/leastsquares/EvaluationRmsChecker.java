package org.apache.commons.math3.fitting.leastsquares;


public class EvaluationRmsChecker implements org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> {
	private final double relTol;

	private final double absTol;

	public EvaluationRmsChecker(final double tol) {
		this(tol, tol);
	}

	public EvaluationRmsChecker(final double relTol ,final double absTol) {
		this.relTol = relTol;
		this.absTol = absTol;
	}

	public boolean converged(final int iteration, final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation previous, final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation current) {
		final double prevRms = previous.getRMS();
		final double currRms = current.getRMS();
		return (org.apache.commons.math3.util.Precision.equals(prevRms, currRms, org.apache.commons.math3.fitting.leastsquares.EvaluationRmsChecker.this.absTol)) || (org.apache.commons.math3.util.Precision.equalsWithRelativeTolerance(prevRms, currRms, org.apache.commons.math3.fitting.leastsquares.EvaluationRmsChecker.this.relTol));
	}
}

