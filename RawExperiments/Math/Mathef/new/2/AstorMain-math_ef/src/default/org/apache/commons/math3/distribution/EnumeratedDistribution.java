package org.apache.commons.math3.distribution;


public class EnumeratedDistribution<T> implements java.io.Serializable {
	private static final long serialVersionUID = 20123308L;

	protected final org.apache.commons.math3.random.RandomGenerator random;

	private final java.util.List<T> singletons;

	private final double[] probabilities;

	private final double[] cumulativeProbabilities;

	public EnumeratedDistribution(final java.util.List<org.apache.commons.math3.util.Pair<T, java.lang.Double>> pmf) throws org.apache.commons.math3.exception.MathArithmeticException , org.apache.commons.math3.exception.NotANumberException , org.apache.commons.math3.exception.NotFiniteNumberException , org.apache.commons.math3.exception.NotPositiveException {
		this(new org.apache.commons.math3.random.Well19937c(), pmf);
	}

	public EnumeratedDistribution(final org.apache.commons.math3.random.RandomGenerator rng ,final java.util.List<org.apache.commons.math3.util.Pair<T, java.lang.Double>> pmf) throws org.apache.commons.math3.exception.MathArithmeticException , org.apache.commons.math3.exception.NotANumberException , org.apache.commons.math3.exception.NotFiniteNumberException , org.apache.commons.math3.exception.NotPositiveException {
		random = rng;
		singletons = new java.util.ArrayList<T>(pmf.size());
		final double[] probs = new double[pmf.size()];
		for (int i = 0 ; i < (pmf.size()) ; i++) {
			final org.apache.commons.math3.util.Pair<T, java.lang.Double> sample = pmf.get(i);
			singletons.add(sample.getKey());
			final double p = sample.getValue();
			if (p < 0) {
				throw new org.apache.commons.math3.exception.NotPositiveException(sample.getValue());
			} 
			if (java.lang.Double.isInfinite(p)) {
				throw new org.apache.commons.math3.exception.NotFiniteNumberException(p);
			} 
			if (java.lang.Double.isNaN(p)) {
				throw new org.apache.commons.math3.exception.NotANumberException();
			} 
			probs[i] = p;
		}
		probabilities = org.apache.commons.math3.util.MathArrays.normalizeArray(probs, 1.0);
		cumulativeProbabilities = new double[probabilities.length];
		double sum = 0;
		for (int i = 0 ; i < (probabilities.length) ; i++) {
			sum += probabilities[i];
			cumulativeProbabilities[i] = sum;
		}
	}

	public void reseedRandomGenerator(long seed) {
		random.setSeed(seed);
	}

	double probability(final T x) {
		double probability = 0;
		for (int i = 0 ; i < (probabilities.length) ; i++) {
			if (((x == null) && ((singletons.get(i)) == null)) || ((x != null) && (x.equals(singletons.get(i))))) {
				probability += probabilities[i];
			} 
		}
		return probability;
	}

	public java.util.List<org.apache.commons.math3.util.Pair<T, java.lang.Double>> getPmf() {
		final java.util.List<org.apache.commons.math3.util.Pair<T, java.lang.Double>> samples = new java.util.ArrayList<org.apache.commons.math3.util.Pair<T, java.lang.Double>>(probabilities.length);
		for (int i = 0 ; i < (probabilities.length) ; i++) {
			samples.add(new org.apache.commons.math3.util.Pair<T, java.lang.Double>(singletons.get(i) , probabilities[i]));
		}
		return samples;
	}

	public T sample() {
		final double randomValue = random.nextDouble();
		int index = java.util.Arrays.binarySearch(cumulativeProbabilities, randomValue);
		if (index < 0) {
			index = (-index) - 1;
		} 
		if ((index >= 0) && (index < (probabilities.length))) {
			if (randomValue < (cumulativeProbabilities[index])) {
				return singletons.get(index);
			} 
		} 
		return singletons.get(((singletons.size()) - 1));
	}

	public java.lang.Object[] sample(int sampleSize) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (sampleSize <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_SAMPLES , sampleSize);
		} 
		final java.lang.Object[] out = new java.lang.Object[sampleSize];
		for (int i = 0 ; i < sampleSize ; i++) {
			out[i] = sample();
		}
		return out;
	}

	public T[] sample(int sampleSize, final T[] array) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		if (sampleSize <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_SAMPLES , sampleSize);
		} 
		if (array == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INPUT_ARRAY);
		} 
		T[] out;
		if ((array.length) < sampleSize) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final T[] unchecked = ((T[])(java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), sampleSize)));
			out = unchecked;
		} else {
			out = array;
		}
		for (int i = 0 ; i < sampleSize ; i++) {
			out[i] = sample();
		}
		return out;
	}
}

