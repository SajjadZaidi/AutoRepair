package org.apache.commons.math3.stat.interval;


public final class IntervalUtils {
	private static final org.apache.commons.math3.stat.interval.BinomialConfidenceInterval AGRESTI_COULL = new org.apache.commons.math3.stat.interval.AgrestiCoullInterval();

	private static final org.apache.commons.math3.stat.interval.BinomialConfidenceInterval CLOPPER_PEARSON = new org.apache.commons.math3.stat.interval.ClopperPearsonInterval();

	private static final org.apache.commons.math3.stat.interval.BinomialConfidenceInterval NORMAL_APPROXIMATION = new org.apache.commons.math3.stat.interval.NormalApproximationInterval();

	private static final org.apache.commons.math3.stat.interval.BinomialConfidenceInterval WILSON_SCORE = new org.apache.commons.math3.stat.interval.WilsonScoreInterval();

	private IntervalUtils() {
	}

	public static org.apache.commons.math3.stat.interval.ConfidenceInterval getAgrestiCoullInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		return org.apache.commons.math3.stat.interval.IntervalUtils.AGRESTI_COULL.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
	}

	public static org.apache.commons.math3.stat.interval.ConfidenceInterval getClopperPearsonInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		return org.apache.commons.math3.stat.interval.IntervalUtils.CLOPPER_PEARSON.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
	}

	public static org.apache.commons.math3.stat.interval.ConfidenceInterval getNormalApproximationInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		return org.apache.commons.math3.stat.interval.IntervalUtils.NORMAL_APPROXIMATION.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
	}

	public static org.apache.commons.math3.stat.interval.ConfidenceInterval getWilsonScoreInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		return org.apache.commons.math3.stat.interval.IntervalUtils.WILSON_SCORE.createInterval(numberOfTrials, numberOfSuccesses, confidenceLevel);
	}

	static void checkParameters(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
		if (numberOfTrials <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_TRIALS , numberOfTrials);
		} 
		if (numberOfSuccesses < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NEGATIVE_NUMBER_OF_SUCCESSES , numberOfSuccesses);
		} 
		if (numberOfSuccesses > numberOfTrials) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE , numberOfSuccesses , numberOfTrials , true);
		} 
		if ((confidenceLevel <= 0) || (confidenceLevel >= 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUNDS_CONFIDENCE_LEVEL , confidenceLevel , 0 , 1);
		} 
	}
}

