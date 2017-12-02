package org.apache.commons.math3.optim.nonlinear.scalar;


public abstract class GradientMultivariateOptimizer extends org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer {
	private org.apache.commons.math3.analysis.MultivariateVectorFunction gradient;

	protected GradientMultivariateOptimizer(org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> checker) {
		super(checker);
	}

	protected double[] computeObjectiveGradient(final double[] params) {
		return gradient.value(params);
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.PointValuePair optimize(org.apache.commons.math3.optim.OptimizationData... optData) throws org.apache.commons.math3.exception.TooManyEvaluationsException {
		return super.optimize(optData);
	}

	@java.lang.Override
	protected void parseOptimizationData(org.apache.commons.math3.optim.OptimizationData... optData) {
		super.parseOptimizationData(optData);
		for (org.apache.commons.math3.optim.OptimizationData data : optData) {
			if (data instanceof org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunctionGradient) {
				gradient = ((org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunctionGradient)(data)).getObjectiveFunctionGradient();
				break;
			} 
		}
	}
}

