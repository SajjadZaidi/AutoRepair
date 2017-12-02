package org.apache.commons.math3.distribution;


public class EnumeratedIntegerDistribution extends org.apache.commons.math3.distribution.AbstractIntegerDistribution {
	private static final long serialVersionUID = 20130308L;

	protected final org.apache.commons.math3.distribution.EnumeratedDistribution<java.lang.Integer> innerDistribution;

	public EnumeratedIntegerDistribution(final int[] singletons ,final double[] probabilities) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.MathArithmeticException , org.apache.commons.math3.exception.NotANumberException , org.apache.commons.math3.exception.NotFiniteNumberException , org.apache.commons.math3.exception.NotPositiveException {
		this(new org.apache.commons.math3.random.Well19937c(), singletons, probabilities);
	}

	public EnumeratedIntegerDistribution(final org.apache.commons.math3.random.RandomGenerator rng ,final int[] singletons ,final double[] probabilities) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.MathArithmeticException , org.apache.commons.math3.exception.NotANumberException , org.apache.commons.math3.exception.NotFiniteNumberException , org.apache.commons.math3.exception.NotPositiveException {
		super(rng);
		if ((singletons.length) != (probabilities.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(probabilities.length , singletons.length);
		} 
		final java.util.List<org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double>> samples = new java.util.ArrayList<org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double>>(singletons.length);
		for (int i = 0 ; i < (singletons.length) ; i++) {
			samples.add(new org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double>(singletons[i] , probabilities[i]));
		}
		innerDistribution = new org.apache.commons.math3.distribution.EnumeratedDistribution<java.lang.Integer>(rng , samples);
	}

	public double probability(final int x) {
		return innerDistribution.probability(x);
	}

	public double cumulativeProbability(final int x) {
		double probability = 0;
		for (final org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double> sample : innerDistribution.getPmf()) {
			if ((sample.getKey()) <= x) {
				probability += sample.getValue();
			} 
		}
		return probability;
	}

	public double getNumericalMean() {
		double mean = 0;
		for (final org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double> sample : innerDistribution.getPmf()) {
			mean += (sample.getValue()) * (sample.getKey());
		}
		return mean;
	}

	public double getNumericalVariance() {
		double mean = 0;
		double meanOfSquares = 0;
		for (final org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double> sample : innerDistribution.getPmf()) {
			mean += (sample.getValue()) * (sample.getKey());
			meanOfSquares += ((sample.getValue()) * (sample.getKey())) * (sample.getKey());
		}
		return meanOfSquares - (mean * mean);
	}

	public int getSupportLowerBound() {
		int min = java.lang.Integer.MAX_VALUE;
		for (final org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double> sample : innerDistribution.getPmf()) {
			if (((sample.getKey()) < min) && ((sample.getValue()) > 0)) {
				min = sample.getKey();
			} 
		}
		return min;
	}

	public int getSupportUpperBound() {
		int max = java.lang.Integer.MIN_VALUE;
		for (final org.apache.commons.math3.util.Pair<java.lang.Integer, java.lang.Double> sample : innerDistribution.getPmf()) {
			if (((sample.getKey()) > max) && ((sample.getValue()) > 0)) {
				max = sample.getKey();
			} 
		}
		return max;
	}

	public boolean isSupportConnected() {
		return true;
	}

	@java.lang.Override
	public int sample() {
		return innerDistribution.sample();
	}
}

