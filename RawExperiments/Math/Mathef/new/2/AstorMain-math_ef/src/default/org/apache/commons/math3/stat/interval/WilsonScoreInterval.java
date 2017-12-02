package org.apache.commons.math3.stat.interval;


public class WilsonScoreInterval implements org.apache.commons.math3.stat.interval.BinomialConfidenceInterval {
	public org.apache.commons.math3.stat.interval.ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		org.apache.commons.math3.stat.interval.IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
		final double alpha = (1.0 - confidenceLevel) / 2;
		final org.apache.commons.math3.distribution.NormalDistribution normalDistribution = new org.apache.commons.math3.distribution.NormalDistribution();
		final double z = normalDistribution.inverseCumulativeProbability((1 - alpha));
		final double zSquared = org.apache.commons.math3.util.FastMath.pow(z, 2);
		final double mean = ((double)(numberOfSuccesses)) / ((double)(numberOfTrials));
		final double factor = 1.0 / (1 + ((1.0 / numberOfTrials) * zSquared));
		final double modifiedSuccessRatio = mean + ((1.0 / (2 * numberOfTrials)) * zSquared);
		final double difference = z * (org.apache.commons.math3.util.FastMath.sqrt(((((1.0 / numberOfTrials) * mean) * (1 - mean)) + ((1.0 / (4 * (org.apache.commons.math3.util.FastMath.pow(numberOfTrials, 2)))) * zSquared))));
		final double lowerBound = factor * (modifiedSuccessRatio - difference);
		final double upperBound = factor * (modifiedSuccessRatio + difference);
		return new org.apache.commons.math3.stat.interval.ConfidenceInterval(lowerBound , upperBound , confidenceLevel);
	}
}

