package org.apache.commons.math3.fitting;


public class PolynomialCurveFitter extends org.apache.commons.math3.fitting.AbstractCurveFitter {
	private static final org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric FUNCTION = new org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric();

	private final double[] initialGuess;

	private final int maxIter;

	private PolynomialCurveFitter(double[] initialGuess ,int maxIter) {
		this.initialGuess = initialGuess;
		this.maxIter = maxIter;
	}

	public static org.apache.commons.math3.fitting.PolynomialCurveFitter create(int degree) {
		return new org.apache.commons.math3.fitting.PolynomialCurveFitter(new double[degree + 1] , java.lang.Integer.MAX_VALUE);
	}

	public org.apache.commons.math3.fitting.PolynomialCurveFitter withStartPoint(double[] newStart) {
		return new org.apache.commons.math3.fitting.PolynomialCurveFitter(newStart.clone() , maxIter);
	}

	public org.apache.commons.math3.fitting.PolynomialCurveFitter withMaxIterations(int newMaxIter) {
		return new org.apache.commons.math3.fitting.PolynomialCurveFitter(initialGuess , newMaxIter);
	}

	@java.lang.Override
	protected org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem getProblem(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> observations) {
		final int len = observations.size();
		final double[] target = new double[len];
		final double[] weights = new double[len];
		int i = 0;
		for (org.apache.commons.math3.fitting.WeightedObservedPoint obs : observations) {
			target[i] = obs.getY();
			weights[i] = obs.getWeight();
			++i;
		}
		final org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction model = new org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction(org.apache.commons.math3.fitting.PolynomialCurveFitter.FUNCTION , observations);
		if ((initialGuess) == null) {
			throw new org.apache.commons.math3.exception.MathInternalError();
		} 
		return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder().maxEvaluations(java.lang.Integer.MAX_VALUE).maxIterations(maxIter).start(initialGuess).target(target).weight(new org.apache.commons.math3.linear.DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
	}
}

