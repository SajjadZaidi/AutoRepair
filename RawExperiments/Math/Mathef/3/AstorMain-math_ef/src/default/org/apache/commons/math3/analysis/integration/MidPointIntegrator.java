package org.apache.commons.math3.analysis.integration;


public class MidPointIntegrator extends org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator {
	public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

	public MidPointIntegrator(final double relativeAccuracy ,final double absoluteAccuracy ,final int minimalIterationCount ,final int maximalIterationCount) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.NumberIsTooLargeException , org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
		if (maximalIterationCount > (org.apache.commons.math3.analysis.integration.MidPointIntegrator.MIDPOINT_MAX_ITERATIONS_COUNT)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(maximalIterationCount , org.apache.commons.math3.analysis.integration.MidPointIntegrator.MIDPOINT_MAX_ITERATIONS_COUNT , false);
		} 
	}

	public MidPointIntegrator(final int minimalIterationCount ,final int maximalIterationCount) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.NumberIsTooLargeException , org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(minimalIterationCount, maximalIterationCount);
		if (maximalIterationCount > (org.apache.commons.math3.analysis.integration.MidPointIntegrator.MIDPOINT_MAX_ITERATIONS_COUNT)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(maximalIterationCount , org.apache.commons.math3.analysis.integration.MidPointIntegrator.MIDPOINT_MAX_ITERATIONS_COUNT , false);
		} 
	}

	public MidPointIntegrator() {
		super(org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator.DEFAULT_MIN_ITERATIONS_COUNT, org.apache.commons.math3.analysis.integration.MidPointIntegrator.MIDPOINT_MAX_ITERATIONS_COUNT);
	}

	private double stage(final int n, double previousStageResult, double min, double diffMaxMin) throws org.apache.commons.math3.exception.TooManyEvaluationsException {
		final long np = 1L << (n - 1);
		double sum = 0;
		final double spacing = diffMaxMin / np;
		double x = min + (0.5 * spacing);
		for (long i = 0 ; i < np ; i++) {
			sum += computeObjectiveValue(x);
			x += spacing;
		}
		return 0.5 * (previousStageResult + (sum * spacing));
	}

	@java.lang.Override
	protected double doIntegrate() throws org.apache.commons.math3.exception.MathIllegalArgumentException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.TooManyEvaluationsException {
		final double min = getMin();
		final double diff = (getMax()) - min;
		final double midPoint = min + (0.5 * diff);
		double oldt = diff * (computeObjectiveValue(midPoint));
		while (true) {
			iterations.incrementCount();
			final int i = iterations.getCount();
			final double t = stage(i, oldt, min, diff);
			if (i >= (getMinimalIterationCount())) {
				final double delta = org.apache.commons.math3.util.FastMath.abs((t - oldt));
				final double rLimit = ((getRelativeAccuracy()) * ((org.apache.commons.math3.util.FastMath.abs(oldt)) + (org.apache.commons.math3.util.FastMath.abs(t)))) * 0.5;
				if ((delta <= rLimit) || (delta <= (getAbsoluteAccuracy()))) {
					return t;
				} 
			} 
			oldt = t;
		}
	}
}

