package org.apache.commons.math3.distribution;


public class BinomialDistribution extends org.apache.commons.math3.distribution.AbstractIntegerDistribution {
	private static final long serialVersionUID = 6751309484392813623L;

	private final int numberOfTrials;

	private final double probabilityOfSuccess;

	public BinomialDistribution(int trials ,double p) {
		this(new org.apache.commons.math3.random.Well19937c(), trials, p);
	}

	public BinomialDistribution(org.apache.commons.math3.random.RandomGenerator rng ,int trials ,double p) {
		super(rng);
		if (trials < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_TRIALS , trials);
		} 
		if ((p < 0) || (p > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		probabilityOfSuccess = p;
		numberOfTrials = trials;
	}

	public int getNumberOfTrials() {
		return numberOfTrials;
	}

	public double getProbabilityOfSuccess() {
		return probabilityOfSuccess;
	}

	public double probability(int x) {
		final double logProbability = logProbability(x);
		return logProbability == (java.lang.Double.NEGATIVE_INFINITY) ? 0 : org.apache.commons.math3.util.FastMath.exp(logProbability);
	}

	@java.lang.Override
	public double logProbability(int x) {
		if ((numberOfTrials) == 0) {
			return x == 0 ? 0.0 : java.lang.Double.NEGATIVE_INFINITY;
		} 
		double ret;
		if ((x < 0) || (x > (numberOfTrials))) {
			ret = java.lang.Double.NEGATIVE_INFINITY;
		} else {
			ret = org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability(x, numberOfTrials, probabilityOfSuccess, (1.0 - (probabilityOfSuccess)));
		}
		return ret;
	}

	public double cumulativeProbability(int x) {
		double ret;
		if (x < 0) {
			ret = 0.0;
		} else if (x >= (numberOfTrials)) {
			ret = 1.0;
		} else {
			ret = 1.0 - (org.apache.commons.math3.special.Beta.regularizedBeta(probabilityOfSuccess, (x + 1.0), ((numberOfTrials) - x)));
		}
		return ret;
	}

	public double getNumericalMean() {
		return (numberOfTrials) * (probabilityOfSuccess);
	}

	public double getNumericalVariance() {
		final double p = probabilityOfSuccess;
		return ((numberOfTrials) * p) * (1 - p);
	}

	public int getSupportLowerBound() {
		return (probabilityOfSuccess) < 1.0 ? 0 : numberOfTrials;
	}

	public int getSupportUpperBound() {
		return (probabilityOfSuccess) > 0.0 ? numberOfTrials : 0;
	}

	public boolean isSupportConnected() {
		return true;
	}
}

