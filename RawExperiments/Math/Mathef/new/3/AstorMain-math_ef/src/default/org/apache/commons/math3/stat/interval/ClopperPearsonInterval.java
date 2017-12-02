package org.apache.commons.math3.stat.interval;


public class ClopperPearsonInterval implements org.apache.commons.math3.stat.interval.BinomialConfidenceInterval {
	public org.apache.commons.math3.stat.interval.ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		org.apache.commons.math3.stat.interval.IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
		double lowerBound = 0;
		double upperBound = 0;
		final double alpha = (1.0 - confidenceLevel) / 2.0;
		final org.apache.commons.math3.distribution.FDistribution distributionLowerBound = new org.apache.commons.math3.distribution.FDistribution((2 * ((numberOfTrials - numberOfSuccesses) + 1)) , (2 * numberOfSuccesses));
		final double fValueLowerBound = distributionLowerBound.inverseCumulativeProbability((1 - alpha));
		if (numberOfSuccesses > 0) {
			lowerBound = numberOfSuccesses / (numberOfSuccesses + (((numberOfTrials - numberOfSuccesses) + 1) * fValueLowerBound));
		} 
		final org.apache.commons.math3.distribution.FDistribution distributionUpperBound = new org.apache.commons.math3.distribution.FDistribution((2 * (numberOfSuccesses + 1)) , (2 * (numberOfTrials - numberOfSuccesses)));
		final double fValueUpperBound = distributionUpperBound.inverseCumulativeProbability((1 - alpha));
		if (numberOfSuccesses > 0) {
			upperBound = ((numberOfSuccesses + 1) * fValueUpperBound) / ((numberOfTrials - numberOfSuccesses) + ((numberOfSuccesses + 1) * fValueUpperBound));
		} 
		return new org.apache.commons.math3.stat.interval.ConfidenceInterval(lowerBound , upperBound , confidenceLevel);
	}
}

