package org.apache.commons.math3.optim;


public class SimpleValueChecker extends org.apache.commons.math3.optim.AbstractConvergenceChecker<org.apache.commons.math3.optim.PointValuePair> {
	private static final int ITERATION_CHECK_DISABLED = -1;

	private final int maxIterationCount;

	public SimpleValueChecker(final double relativeThreshold ,final double absoluteThreshold) {
		super(relativeThreshold, absoluteThreshold);
		maxIterationCount = org.apache.commons.math3.optim.SimpleValueChecker.ITERATION_CHECK_DISABLED;
	}

	public SimpleValueChecker(final double relativeThreshold ,final double absoluteThreshold ,final int maxIter) {
		super(relativeThreshold, absoluteThreshold);
		if (maxIter <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(maxIter);
		} 
		maxIterationCount = maxIter;
	}

	@java.lang.Override
	public boolean converged(final int iteration, final org.apache.commons.math3.optim.PointValuePair previous, final org.apache.commons.math3.optim.PointValuePair current) {
		if (((maxIterationCount) != (org.apache.commons.math3.optim.SimpleValueChecker.ITERATION_CHECK_DISABLED)) && (iteration >= (maxIterationCount))) {
			return true;
		} 
		final double p = previous.getValue();
		final double c = current.getValue();
		final double difference = org.apache.commons.math3.util.FastMath.abs((p - c));
		final double size = org.apache.commons.math3.util.FastMath.max(org.apache.commons.math3.util.FastMath.abs(p), org.apache.commons.math3.util.FastMath.abs(c));
		return (difference <= (size * (getRelativeThreshold()))) || (difference <= (getAbsoluteThreshold()));
	}
}

