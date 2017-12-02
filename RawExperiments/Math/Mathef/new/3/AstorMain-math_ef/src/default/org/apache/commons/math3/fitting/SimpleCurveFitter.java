package org.apache.commons.math3.fitting;


public class SimpleCurveFitter extends org.apache.commons.math3.fitting.AbstractCurveFitter {
	private final org.apache.commons.math3.analysis.ParametricUnivariateFunction function;

	private final double[] initialGuess;

	private final int maxIter;

	private SimpleCurveFitter(org.apache.commons.math3.analysis.ParametricUnivariateFunction function ,double[] initialGuess ,int maxIter) {
		this.function = function;
		this.initialGuess = initialGuess;
		this.maxIter = maxIter;
	}

	public static org.apache.commons.math3.fitting.SimpleCurveFitter create(org.apache.commons.math3.analysis.ParametricUnivariateFunction f, double[] start) {
		return new org.apache.commons.math3.fitting.SimpleCurveFitter(f , start , java.lang.Integer.MAX_VALUE);
	}

	public org.apache.commons.math3.fitting.SimpleCurveFitter withStartPoint(double[] newStart) {
		return new org.apache.commons.math3.fitting.SimpleCurveFitter(function , newStart.clone() , maxIter);
	}

	public org.apache.commons.math3.fitting.SimpleCurveFitter withMaxIterations(int newMaxIter) {
		return new org.apache.commons.math3.fitting.SimpleCurveFitter(function , initialGuess , newMaxIter);
	}

	@java.lang.Override
	protected org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem getProblem(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> observations) {
		final int len = observations.size();
		final double[] target = new double[len];
		final double[] weights = new double[len];
		int count = 0;
		for (org.apache.commons.math3.fitting.WeightedObservedPoint obs : observations) {
			target[count] = obs.getY();
			weights[count] = obs.getWeight();
			++count;
		}
		final org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction model = new org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction(function , observations);
		return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder().maxEvaluations(java.lang.Integer.MAX_VALUE).maxIterations(maxIter).start(initialGuess).target(target).weight(new org.apache.commons.math3.linear.DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
	}
}

