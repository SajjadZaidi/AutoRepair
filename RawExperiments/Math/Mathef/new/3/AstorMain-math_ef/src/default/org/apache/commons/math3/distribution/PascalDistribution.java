package org.apache.commons.math3.distribution;


public class PascalDistribution extends org.apache.commons.math3.distribution.AbstractIntegerDistribution {
	private static final long serialVersionUID = 6751309484392813623L;

	private final int numberOfSuccesses;

	private final double probabilityOfSuccess;

	private final double logProbabilityOfSuccess;

	private final double log1mProbabilityOfSuccess;

	public PascalDistribution(int r ,double p) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.OutOfRangeException {
		this(new org.apache.commons.math3.random.Well19937c(), r, p);
	}

	public PascalDistribution(org.apache.commons.math3.random.RandomGenerator rng ,int r ,double p) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.OutOfRangeException {
		super(rng);
		if (r <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_SUCCESSES , r);
		} 
		if ((p < 0) || (p > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		numberOfSuccesses = r;
		probabilityOfSuccess = p;
		logProbabilityOfSuccess = org.apache.commons.math3.util.FastMath.log(p);
		log1mProbabilityOfSuccess = org.apache.commons.math3.util.FastMath.log1p((-p));
	}

	public int getNumberOfSuccesses() {
		return numberOfSuccesses;
	}

	public double getProbabilityOfSuccess() {
		return probabilityOfSuccess;
	}

	public double probability(int x) {
		double ret;
		if (x < 0) {
			ret = 0.0;
		} else {
			ret = ((org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble(((x + (numberOfSuccesses)) - 1), ((numberOfSuccesses) - 1))) * (org.apache.commons.math3.util.FastMath.pow(probabilityOfSuccess, numberOfSuccesses))) * (org.apache.commons.math3.util.FastMath.pow((1.0 - (probabilityOfSuccess)), x));
		}
		return ret;
	}

	@java.lang.Override
	public double logProbability(int x) {
		double ret;
		if (x < 0) {
			ret = java.lang.Double.NEGATIVE_INFINITY;
		} else {
			ret = ((org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog(((x + (numberOfSuccesses)) - 1), ((numberOfSuccesses) - 1))) + ((logProbabilityOfSuccess) * (numberOfSuccesses))) + ((log1mProbabilityOfSuccess) * x);
		}
		return ret;
	}

	public double cumulativeProbability(int x) {
		double ret;
		if (x < 0) {
			ret = 0.0;
		} else {
			ret = org.apache.commons.math3.special.Beta.regularizedBeta(probabilityOfSuccess, numberOfSuccesses, (x + 1.0));
		}
		return ret;
	}

	public double getNumericalMean() {
		final double p = getProbabilityOfSuccess();
		final double r = getNumberOfSuccesses();
		return (r * (1 - p)) / p;
	}

	public double getNumericalVariance() {
		final double p = getProbabilityOfSuccess();
		final double r = getNumberOfSuccesses();
		return (r * (1 - p)) / (p * p);
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

