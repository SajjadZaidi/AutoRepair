package org.apache.commons.math3.fitting.leastsquares;


class OptimumImpl implements org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum {
	private final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation value;

	private final int evaluations;

	private final int iterations;

	OptimumImpl(final org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation value ,final int evaluations ,final int iterations) {
		this.value = value;
		this.evaluations = evaluations;
		this.iterations = iterations;
	}

	public int getEvaluations() {
		return evaluations;
	}

	public int getIterations() {
		return iterations;
	}

	public org.apache.commons.math3.linear.RealMatrix getCovariances(double threshold) {
		return value.getCovariances(threshold);
	}

	public org.apache.commons.math3.linear.RealVector getSigma(double covarianceSingularityThreshold) {
		return value.getSigma(covarianceSingularityThreshold);
	}

	public double getRMS() {
		return value.getRMS();
	}

	public org.apache.commons.math3.linear.RealMatrix getJacobian() {
		return value.getJacobian();
	}

	public double getCost() {
		return value.getCost();
	}

	public org.apache.commons.math3.linear.RealVector getResiduals() {
		return value.getResiduals();
	}

	public org.apache.commons.math3.linear.RealVector getPoint() {
		return value.getPoint();
	}
}

