package org.apache.commons.math3.distribution;


public interface IntegerDistribution {
	double probability(int x);

	double cumulativeProbability(int x);

	double cumulativeProbability(int x0, int x1) throws org.apache.commons.math3.exception.NumberIsTooLargeException;

	int inverseCumulativeProbability(double p) throws org.apache.commons.math3.exception.OutOfRangeException;

	double getNumericalMean();

	double getNumericalVariance();

	int getSupportLowerBound();

	int getSupportUpperBound();

	boolean isSupportConnected();

	void reseedRandomGenerator(long seed);

	int sample();

	int[] sample(int sampleSize);
}

