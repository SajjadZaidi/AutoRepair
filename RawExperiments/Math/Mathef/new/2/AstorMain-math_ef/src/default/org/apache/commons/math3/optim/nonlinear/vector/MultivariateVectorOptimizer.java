package org.apache.commons.math3.optim.nonlinear.vector;


@java.lang.Deprecated
public abstract class MultivariateVectorOptimizer extends org.apache.commons.math3.optim.BaseMultivariateOptimizer<org.apache.commons.math3.optim.PointVectorValuePair> {
	private double[] target;

	private org.apache.commons.math3.linear.RealMatrix weightMatrix;

	private org.apache.commons.math3.analysis.MultivariateVectorFunction model;

	protected MultivariateVectorOptimizer(org.apache.commons.math3.optim.ConvergenceChecker<org.apache.commons.math3.optim.PointVectorValuePair> checker) {
		super(checker);
	}

	protected double[] computeObjectiveValue(double[] params) {
		super.incrementEvaluationCount();
		return model.value(params);
	}

	@java.lang.Override
	public org.apache.commons.math3.optim.PointVectorValuePair optimize(org.apache.commons.math3.optim.OptimizationData... optData) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.TooManyEvaluationsException {
		return super.optimize(optData);
	}

	public org.apache.commons.math3.linear.RealMatrix getWeight() {
		return weightMatrix.copy();
	}

	public double[] getTarget() {
		return target.clone();
	}

	public int getTargetSize() {
		return target.length;
	}

	@java.lang.Override
	protected void parseOptimizationData(org.apache.commons.math3.optim.OptimizationData... optData) {
		super.parseOptimizationData(optData);
		for (org.apache.commons.math3.optim.OptimizationData data : optData) {
			if (data instanceof org.apache.commons.math3.optim.nonlinear.vector.ModelFunction) {
				model = ((org.apache.commons.math3.optim.nonlinear.vector.ModelFunction)(data)).getModelFunction();
				continue;
			} 
			if (data instanceof org.apache.commons.math3.optim.nonlinear.vector.Target) {
				target = ((org.apache.commons.math3.optim.nonlinear.vector.Target)(data)).getTarget();
				continue;
			} 
			if (data instanceof org.apache.commons.math3.optim.nonlinear.vector.Weight) {
				weightMatrix = ((org.apache.commons.math3.optim.nonlinear.vector.Weight)(data)).getWeight();
				continue;
			} 
		}
		checkParameters();
	}

	private void checkParameters() {
		if ((target.length) != (weightMatrix.getColumnDimension())) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(target.length , weightMatrix.getColumnDimension());
		} 
	}
}

