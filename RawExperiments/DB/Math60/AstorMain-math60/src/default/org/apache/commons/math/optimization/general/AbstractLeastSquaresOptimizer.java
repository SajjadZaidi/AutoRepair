package org.apache.commons.math.optimization.general;


public abstract class AbstractLeastSquaresOptimizer extends org.apache.commons.math.optimization.direct.BaseAbstractVectorialOptimizer<org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction> implements org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer {
	protected double[][] weightedResidualJacobian;

	protected int cols;

	protected int rows;

	protected double[] point;

	protected double[] objective;

	protected double[] residuals;

	protected double[] weightedResiduals;

	protected double cost;

	private org.apache.commons.math.analysis.MultivariateMatrixFunction jF;

	private int jacobianEvaluations;

	protected AbstractLeastSquaresOptimizer() {
	}

	protected AbstractLeastSquaresOptimizer(org.apache.commons.math.optimization.ConvergenceChecker<org.apache.commons.math.optimization.VectorialPointValuePair> checker ,int maxEvaluations) {
		super(checker, maxEvaluations);
	}

	public int getJacobianEvaluations() {
		return jacobianEvaluations;
	}

	protected void updateJacobian() {
		++(jacobianEvaluations);
		weightedResidualJacobian = jF.value(point);
		if ((weightedResidualJacobian.length) != (rows)) {
			throw new org.apache.commons.math.exception.DimensionMismatchException(weightedResidualJacobian.length , rows);
		} 
		final double[] residualsWeights = getWeightRef();
		for (int i = 0 ; i < (rows) ; i++) {
			final double[] ji = weightedResidualJacobian[i];
			double wi = org.apache.commons.math.util.FastMath.sqrt(residualsWeights[i]);
			for (int j = 0 ; j < (cols) ; ++j) {
				weightedResidualJacobian[i][j] = (-(ji[j])) * wi;
			}
		}
	}

	protected void updateResidualsAndCost() {
		objective = computeObjectiveValue(point);
		if ((objective.length) != (rows)) {
			throw new org.apache.commons.math.exception.DimensionMismatchException(objective.length , rows);
		} 
		final double[] targetValues = getTargetRef();
		final double[] residualsWeights = getWeightRef();
		cost = 0;
		int index = 0;
		for (int i = 0 ; i < (rows) ; i++) {
			final double residual = (targetValues[i]) - (objective[i]);
			weightedResiduals[i] = residual * (org.apache.commons.math.util.FastMath.sqrt(residualsWeights[i]));
			cost += ((residualsWeights[i]) * residual) * residual;
			index += cols;
		}
		cost = org.apache.commons.math.util.FastMath.sqrt(cost);
	}

	public double getRMS() {
		return org.apache.commons.math.util.FastMath.sqrt(((getChiSquare()) / (rows)));
	}

	public double getChiSquare() {
		return (cost) * (cost);
	}

	public double[][] getCovariances() {
		updateJacobian();
		double[][] jTj = new double[cols][cols];
		for (int i = 0 ; i < (cols) ; ++i) {
			for (int j = i ; j < (cols) ; ++j) {
				double sum = 0;
				for (int k = 0 ; k < (rows) ; ++k) {
					sum += (weightedResidualJacobian[k][i]) * (weightedResidualJacobian[k][j]);
				}
				jTj[i][j] = sum;
				jTj[j][i] = sum;
			}
		}
		org.apache.commons.math.linear.RealMatrix inverse = new org.apache.commons.math.linear.LUDecompositionImpl(org.apache.commons.math.linear.MatrixUtils.createRealMatrix(jTj)).getSolver().getInverse();
		return inverse.getData();
	}

	public double[] guessParametersErrors() {
		if ((rows) <= (cols)) {
			throw new org.apache.commons.math.exception.NumberIsTooSmallException(org.apache.commons.math.exception.util.LocalizedFormats.NO_DEGREES_OF_FREEDOM , rows , cols , false);
		} 
		double[] errors = new double[cols];
		final double c = org.apache.commons.math.util.FastMath.sqrt(((getChiSquare()) / ((rows) - (cols))));
		double[][] covar = getCovariances();
		for (int i = 0 ; i < (errors.length) ; ++i) {
			errors[i] = (org.apache.commons.math.util.FastMath.sqrt(covar[i][i])) * c;
		}
		return errors;
	}

	@java.lang.Override
	public org.apache.commons.math.optimization.VectorialPointValuePair optimize(final org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction f, final double[] target, final double[] weights, final double[] startPoint) {
		jacobianEvaluations = 0;
		jF = f.jacobian();
		org.apache.commons.math.optimization.general.AbstractLeastSquaresOptimizer.this.residuals = new double[target.length];
		point = startPoint.clone();
		rows = target.length;
		cols = point.length;
		weightedResidualJacobian = new double[rows][cols];
		org.apache.commons.math.optimization.general.AbstractLeastSquaresOptimizer.this.weightedResiduals = new double[rows];
		cost = java.lang.Double.POSITIVE_INFINITY;
		return super.optimize(f, target, weights, startPoint);
	}
}

