package org.apache.commons.math3.distribution;


public class GeometricDistribution extends org.apache.commons.math3.distribution.AbstractIntegerDistribution {
	private static final long serialVersionUID = 20130507L;

	private final double probabilityOfSuccess;

	public GeometricDistribution(double p) {
		this(new org.apache.commons.math3.random.Well19937c(), p);
	}

	public GeometricDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double p) {
		super(rng);
		if ((p <= 0) || (p > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE_LEFT , p , 0 , 1);
		} 
		probabilityOfSuccess = p;
	}

	public double getProbabilityOfSuccess() {
		return probabilityOfSuccess;
	}

	public double probability(int x) {
		double ret;
		if (x < 0) {
			ret = 0.0;
		} else {
			final double p = probabilityOfSuccess;
			ret = (org.apache.commons.math3.util.FastMath.pow((1 - p), x)) * p;
		}
		return ret;
	}

	@java.lang.Override
	public double logProbability(int x) {
		double ret;
		if (x < 0) {
			ret = java.lang.Double.NEGATIVE_INFINITY;
		} else {
			final double p = probabilityOfSuccess;
			ret = (x * (org.apache.commons.math3.util.FastMath.log1p((-p)))) + (org.apache.commons.math3.util.FastMath.log(p));
		}
		return ret;
	}

	public double cumulativeProbability(int x) {
		double ret;
		if (x < 0) {
			ret = 0.0;
		} else {
			final double p = probabilityOfSuccess;
			ret = 1.0 - (org.apache.commons.math3.util.FastMath.pow((1 - p), (x + 1)));
		}
		return ret;
	}

	public double getNumericalMean() {
		final double p = probabilityOfSuccess;
		return (1 - p) / p;
	}

	public double getNumericalVariance() {
		final double p = probabilityOfSuccess;
		return (1 - p) / (p * p);
	}

	public int getSupportLowerBound() {
		return 0;
	}

	public int getSupportUpperBound() {
		return java.lang.Integer.MAX_VALUE;
	}

	public boolean isSupportConnected() {
		return true;
	}
}

