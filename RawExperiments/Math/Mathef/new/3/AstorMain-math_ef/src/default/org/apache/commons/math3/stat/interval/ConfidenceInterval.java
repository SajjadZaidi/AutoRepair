package org.apache.commons.math3.stat.interval;


public class ConfidenceInterval {
	private double lowerBound;

	private double upperBound;

	private double confidenceLevel;

	public ConfidenceInterval(double lowerBound ,double upperBound ,double confidenceLevel) {
		checkParameters(lowerBound, upperBound, confidenceLevel);
		org.apache.commons.math3.stat.interval.ConfidenceInterval.this.lowerBound = lowerBound;
		org.apache.commons.math3.stat.interval.ConfidenceInterval.this.upperBound = upperBound;
		org.apache.commons.math3.stat.interval.ConfidenceInterval.this.confidenceLevel = confidenceLevel;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public double getConfidenceLevel() {
		return confidenceLevel;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return ((((("[" + (lowerBound)) + ";") + (upperBound)) + "] (confidence level:") + (confidenceLevel)) + ")";
	}

	private void checkParameters(double lower, double upper, double confidence) {
		if (lower >= upper) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND , lower , upper);
		} 
		if ((confidence <= 0) || (confidence >= 1)) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_BOUNDS_CONFIDENCE_LEVEL , confidence , 0 , 1);
		} 
	}
}

