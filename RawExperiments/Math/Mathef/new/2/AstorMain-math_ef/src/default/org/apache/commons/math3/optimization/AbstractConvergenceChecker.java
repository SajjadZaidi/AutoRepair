package org.apache.commons.math3.optimization;


@java.lang.Deprecated
public abstract class AbstractConvergenceChecker<PAIR> implements org.apache.commons.math3.optimization.ConvergenceChecker<PAIR> {
	@java.lang.Deprecated
	private static final double DEFAULT_RELATIVE_THRESHOLD = 100 * (org.apache.commons.math3.util.Precision.EPSILON);

	@java.lang.Deprecated
	private static final double DEFAULT_ABSOLUTE_THRESHOLD = 100 * (org.apache.commons.math3.util.Precision.SAFE_MIN);

	private final double relativeThreshold;

	private final double absoluteThreshold;

	@java.lang.Deprecated
	public AbstractConvergenceChecker() {
		this.relativeThreshold = org.apache.commons.math3.optimization.AbstractConvergenceChecker.DEFAULT_RELATIVE_THRESHOLD;
		this.absoluteThreshold = org.apache.commons.math3.optimization.AbstractConvergenceChecker.DEFAULT_ABSOLUTE_THRESHOLD;
	}

	public AbstractConvergenceChecker(final double relativeThreshold ,final double absoluteThreshold) {
		this.relativeThreshold = relativeThreshold;
		this.absoluteThreshold = absoluteThreshold;
	}

	public double getRelativeThreshold() {
		return relativeThreshold;
	}

	public double getAbsoluteThreshold() {
		return absoluteThreshold;
	}

	public abstract boolean converged(int iteration, PAIR previous, PAIR current);
}

