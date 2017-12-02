package org.apache.commons.math3.distribution;


public class EnumeratedRealDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	private static final long serialVersionUID = 20130308L;

	protected final org.apache.commons.math3.distribution.EnumeratedDistribution<java.lang.Double> innerDistribution;

	public EnumeratedRealDistribution(final double[] singletons ,final double[] probabilities) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.MathArithmeticException , org.apache.commons.math3.exception.NotANumberException , org.apache.commons.math3.exception.NotFiniteNumberException , org.apache.commons.math3.exception.NotPositiveException {
		this(new org.apache.commons.math3.random.Well19937c(), singletons, probabilities);
	}

	public EnumeratedRealDistribution(final org.apache.commons.math3.random.RandomGenerator rng ,final double[] singletons ,final double[] probabilities) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.MathArithmeticException , org.apache.commons.math3.exception.NotANumberException , org.apache.commons.math3.exception.NotFiniteNumberException , org.apache.commons.math3.exception.NotPositiveException {
		super(rng);
		if ((singletons.length) != (probabilities.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(probabilities.length , singletons.length);
		} 
		java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>> samples = new java.util.ArrayList<org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>>(singletons.length);
		for (int i = 0 ; i < (singletons.length) ; i++) {
			samples.add(new org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double>(singletons[i] , probabilities[i]));
		}
		innerDistribution = new org.apache.commons.math3.distribution.EnumeratedDistribution<java.lang.Double>(rng , samples);
	}

	@java.lang.Override
	public double probability(final double x) {
		return innerDistribution.probability(x);
	}

	public double density(final double x) {
		return probability(x);
	}

	public double cumulativeProbability(final double x) {
		double probability = 0;
		for (final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> sample : innerDistribution.getPmf()) {
			if ((sample.getKey()) <= x) {
				probability += sample.getValue();
			} 
		}
		return probability;
	}

	@java.lang.Override
	public double inverseCumulativeProbability(final double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		double probability = 0;
		double x = getSupportLowerBound();
		for (final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> sample : innerDistribution.getPmf()) {
			if ((sample.getValue()) == 0.0) {
				continue;
			} 
			probability += sample.getValue();
			x = sample.getKey();
			if (probability >= p) {
				break;
			} 
		}
		return x;
	}

	public double getNumericalMean() {
		double mean = 0;
		for (final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> sample : innerDistribution.getPmf()) {
			mean += (sample.getValue()) * (sample.getKey());
		}
		return mean;
	}

	public double getNumericalVariance() {
		double mean = 0;
		double meanOfSquares = 0;
		for (final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> sample : innerDistribution.getPmf()) {
			mean += (sample.getValue()) * (sample.getKey());
			meanOfSquares += ((sample.getValue()) * (sample.getKey())) * (sample.getKey());
		}
		return meanOfSquares - (mean * mean);
	}

	public double getSupportLowerBound() {
		double min = java.lang.Double.POSITIVE_INFINITY;
		for (final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> sample : innerDistribution.getPmf()) {
			if (((sample.getKey()) < min) && ((sample.getValue()) > 0)) {
				min = sample.getKey();
			} 
		}
		return min;
	}

	public double getSupportUpperBound() {
		double max = java.lang.Double.NEGATIVE_INFINITY;
		for (final org.apache.commons.math3.util.Pair<java.lang.Double, java.lang.Double> sample : innerDistribution.getPmf()) {
			if (((sample.getKey()) > max) && ((sample.getValue()) > 0)) {
				max = sample.getKey();
			} 
		}
		return max;
	}

	public boolean isSupportLowerBoundInclusive() {
		return true;
	}

	public boolean isSupportUpperBoundInclusive() {
		return true;
	}

	public boolean isSupportConnected() {
		return true;
	}

	@java.lang.Override
	public double sample() {
		return innerDistribution.sample();
	}
}

