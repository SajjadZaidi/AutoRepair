package org.apache.commons.math3.distribution;


public class BetaDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;

	private static final long serialVersionUID = -1221965979403477668L;

	private final double alpha;

	private final double beta;

	private double z;

	private final double solverAbsoluteAccuracy;

	public BetaDistribution(double alpha ,double beta) {
		this(alpha, beta, org.apache.commons.math3.distribution.BetaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public BetaDistribution(double alpha ,double beta ,double inverseCumAccuracy) {
		this(new org.apache.commons.math3.random.Well19937c(), alpha, beta, inverseCumAccuracy);
	}

	public BetaDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double alpha ,double beta) {
		this(rng, alpha, beta, org.apache.commons.math3.distribution.BetaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public BetaDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double alpha ,double beta ,double inverseCumAccuracy) {
		super(rng);
		this.alpha = alpha;
		this.beta = beta;
		z = java.lang.Double.NaN;
		solverAbsoluteAccuracy = inverseCumAccuracy;
	}

	public double getAlpha() {
		return alpha;
	}

	public double getBeta() {
		return beta;
	}

	private void recomputeZ() {
		if (java.lang.Double.isNaN(z)) {
			z = ((org.apache.commons.math3.special.Gamma.logGamma(alpha)) + (org.apache.commons.math3.special.Gamma.logGamma(beta))) - (org.apache.commons.math3.special.Gamma.logGamma(((alpha) + (beta))));
		} 
	}

	public double density(double x) {
		final double logDensity = logDensity(x);
		return logDensity == (java.lang.Double.NEGATIVE_INFINITY) ? 0 : org.apache.commons.math3.util.FastMath.exp(logDensity);
	}

	@java.lang.Override
	public double logDensity(double x) {
		recomputeZ();
		if ((x < 0) || (x > 1)) {
			return java.lang.Double.NEGATIVE_INFINITY;
		} else if (x == 0) {
			if ((alpha) < 1) {
				throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA , alpha , 1 , false);
			} 
			return java.lang.Double.NEGATIVE_INFINITY;
		} else if (x == 1) {
			if ((beta) < 1) {
				throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA , beta , 1 , false);
			} 
			return java.lang.Double.NEGATIVE_INFINITY;
		} else {
			double logX = org.apache.commons.math3.util.FastMath.log(x);
			double log1mX = org.apache.commons.math3.util.FastMath.log1p((-x));
			return ((((alpha) - 1) * logX) + (((beta) - 1) * log1mX)) - (z);
		}
	}

	public double cumulativeProbability(double x) {
		if (x <= 0) {
			return 0;
		} else if (x >= 1) {
			return 1;
		} else {
			return org.apache.commons.math3.special.Beta.regularizedBeta(x, alpha, beta);
		}
	}

	@java.lang.Override
	protected double getSolverAbsoluteAccuracy() {
		return solverAbsoluteAccuracy;
	}

	public double getNumericalMean() {
		final double a = getAlpha();
		return a / (a + (getBeta()));
	}

	public double getNumericalVariance() {
		final double a = getAlpha();
		final double b = getBeta();
		final double alphabetasum = a + b;
		return (a * b) / ((alphabetasum * alphabetasum) * (alphabetasum + 1));
	}

	public double getSupportLowerBound() {
		return 0;
	}

	public double getSupportUpperBound() {
		return 1;
	}

	public boolean isSupportLowerBoundInclusive() {
		return false;
	}

	public boolean isSupportUpperBoundInclusive() {
		return false;
	}

	public boolean isSupportConnected() {
		return true;
	}
}

