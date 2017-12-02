package org.apache.commons.math3.stat.interval;


public class NormalApproximationInterval implements org.apache.commons.math3.stat.interval.BinomialConfidenceInterval {
	public org.apache.commons.math3.stat.interval.ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		org.apache.commons.math3.stat.interval.IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
		final double mean = ((double)(numberOfSuccesses)) / ((double)(numberOfTrials));
		final double alpha = (1.0 - confidenceLevel) / 2;
		final org.apache.commons.math3.distribution.NormalDistribution normalDistribution = new org.apache.commons.math3.distribution.NormalDistribution();
		final double difference = (normalDistribution.inverseCumulativeProbability((1 - alpha))) * (org.apache.commons.math3.util.FastMath.sqrt((((1.0 / numberOfTrials) * mean) * (1 - mean))));
		return new org.apache.commons.math3.stat.interval.ConfidenceInterval((mean - difference) , (mean + difference) , confidenceLevel);
	}
}

