package org.apache.commons.math3.fitting.leastsquares;


public class LeastSquaresBuilder {
	private int maxEvaluations;

	private int maxIterations;

	private org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> checker;

	private org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction model;

	private org.apache.commons.math3.linear.RealVector target;

	private org.apache.commons.math3.linear.RealVector start;

	private org.apache.commons.math3.linear.RealMatrix weight;

	private boolean lazyEvaluation;

	private org.apache.commons.math3.fitting.leastsquares.ParameterValidator paramValidator;

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem build() {
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.create(model, target, start, weight, checker, maxEvaluations, maxIterations, lazyEvaluation, paramValidator);
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder maxEvaluations(final int newMaxEvaluations) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.maxEvaluations = newMaxEvaluations;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder maxIterations(final int newMaxIterations) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.maxIterations = newMaxIterations;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder checker(final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation> newChecker) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.checker = newChecker;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder checkerPair(final org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointVectorValuePair> newChecker) {
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.checker(org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.evaluationChecker(newChecker));
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder model(final org.apache.commons.math3.analysis.MultivariateVectorFunction value, final org.apache.commons.math3.analysis.MultivariateMatrixFunction jacobian) {
		return model(org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model(value, jacobian));
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder model(final org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction newModel) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.model = newModel;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder target(final org.apache.commons.math3.linear.RealVector newTarget) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.target = newTarget;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder target(final double[] newTarget) {
		return target(new org.apache.commons.math3.linear.ArrayRealVector(newTarget , false));
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder start(final org.apache.commons.math3.linear.RealVector newStart) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.start = newStart;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder start(final double[] newStart) {
		return start(new org.apache.commons.math3.linear.ArrayRealVector(newStart , false));
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder weight(final org.apache.commons.math3.linear.RealMatrix newWeight) {
		org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this.weight = newWeight;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder lazyEvaluation(final boolean newValue) {
		lazyEvaluation = newValue;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}

	public org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder parameterValidator(final org.apache.commons.math3.fitting.leastsquares.ParameterValidator newValidator) {
		paramValidator = newValidator;
		return org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder.this;
	}
}

