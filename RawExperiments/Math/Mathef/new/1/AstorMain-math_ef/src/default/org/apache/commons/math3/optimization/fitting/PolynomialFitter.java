package org.apache.commons.math3.optimization.fitting;


@java.lang.Deprecated
public class PolynomialFitter extends org.apache.commons.math3.optimization.fitting.CurveFitter<org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric> {
	@java.lang.Deprecated
	private final int degree;

	@java.lang.Deprecated
	public PolynomialFitter(int degree ,final org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer optimizer) {
		super(optimizer);
		this.degree = degree;
	}

	public PolynomialFitter(org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer optimizer) {
		super(optimizer);
		degree = -1;
	}

	@java.lang.Deprecated
	public double[] fit() {
		return fit(new org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric(), new double[(degree) + 1]);
	}

	public double[] fit(int maxEval, double[] guess) {
		return fit(maxEval, new org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric(), guess);
	}

	public double[] fit(double[] guess) {
		return fit(new org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric(), guess);
	}
}

