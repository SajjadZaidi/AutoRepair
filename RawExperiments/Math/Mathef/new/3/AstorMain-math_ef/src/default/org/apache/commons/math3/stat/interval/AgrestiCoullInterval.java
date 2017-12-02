package org.apache.commons.math3.stat.interval;


public class AgrestiCoullInterval implements org.apache.commons.math3.stat.interval.BinomialConfidenceInterval {
	public org.apache.commons.math3.stat.interval.ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		org.apache.commons.math3.stat.interval.IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
		final double alpha = (1.0 - confidenceLevel) / 2;
		final org.apache.commons.math3.distribution.NormalDistribution normalDistribution = new org.apache.commons.math3.distribution.NormalDistribution();
		final double z = normalDistribution.inverseCumulativeProbability((1 - alpha));
		final double zSquared = org.apache.commons.math3.util.FastMath.pow(z, 2);
		final double modifiedNumberOfTrials = numberOfTrials + zSquared;
		final double modifiedSuccessesRatio = (1.0 / modifiedNumberOfTrials) * (numberOfSuccesses + (0.5 * zSquared));
		final double difference = z * (org.apache.commons.math3.util.FastMath.sqrt((((1.0 / modifiedNumberOfTrials) * modifiedSuccessesRatio) * (1 - modifiedSuccessesRatio))));
		return new org.apache.commons.math3.stat.interval.ConfidenceInterval((modifiedSuccessesRatio - difference) , (modifiedSuccessesRatio + difference) , confidenceLevel);
	}
}

