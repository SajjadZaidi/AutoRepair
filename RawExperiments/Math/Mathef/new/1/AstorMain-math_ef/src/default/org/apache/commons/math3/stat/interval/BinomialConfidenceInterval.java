package org.apache.commons.math3.stat.interval;


public interface BinomialConfidenceInterval {
	org.apache.commons.math3.stat.interval.ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) throws org.apache.commons.math3.exception.NotPositiveException, org.apache.commons.math3.exception.NotStrictlyPositiveException, org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.OutOfRangeException;
}

