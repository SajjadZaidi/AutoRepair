package org.apache.commons.math3.analysis.integration;


public class RombergIntegrator extends org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator {
	public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;

	public RombergIntegrator(final double relativeAccuracy ,final double absoluteAccuracy ,final int minimalIterationCount ,final int maximalIterationCount) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.NumberIsTooLargeException , org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
		if (maximalIterationCount > (org.apache.commons.math3.analysis.integration.RombergIntegrator.ROMBERG_MAX_ITERATIONS_COUNT)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(maximalIterationCount , org.apache.commons.math3.analysis.integration.RombergIntegrator.ROMBERG_MAX_ITERATIONS_COUNT , false);
		} 
	}

	public RombergIntegrator(final int minimalIterationCount ,final int maximalIterationCount) throws org.apache.commons.math3.exception.NotStrictlyPositiveException , org.apache.commons.math3.exception.NumberIsTooLargeException , org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(minimalIterationCount, maximalIterationCount);
		if (maximalIterationCount > (org.apache.commons.math3.analysis.integration.RombergIntegrator.ROMBERG_MAX_ITERATIONS_COUNT)) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(maximalIterationCount , org.apache.commons.math3.analysis.integration.RombergIntegrator.ROMBERG_MAX_ITERATIONS_COUNT , false);
		} 
	}

	public RombergIntegrator() {
		super(org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator.DEFAULT_MIN_ITERATIONS_COUNT, org.apache.commons.math3.analysis.integration.RombergIntegrator.ROMBERG_MAX_ITERATIONS_COUNT);
	}

	@java.lang.Override
	protected double doIntegrate() throws org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.TooManyEvaluationsException {
		final int m = (iterations.getMaximalCount()) + 1;
		double[] previousRow = new double[m];
		double[] currentRow = new double[m];
		org.apache.commons.math3.analysis.integration.TrapezoidIntegrator qtrap = new org.apache.commons.math3.analysis.integration.TrapezoidIntegrator();
		currentRow[0] = qtrap.stage(org.apache.commons.math3.analysis.integration.RombergIntegrator.this, 0);
		iterations.incrementCount();
		double olds = currentRow[0];
		while (true) {
			final int i = iterations.getCount();
			final double[] tmpRow = previousRow;
			previousRow = currentRow;
			currentRow = tmpRow;
			currentRow[0] = qtrap.stage(org.apache.commons.math3.analysis.integration.RombergIntegrator.this, i);
			iterations.incrementCount();
			for (int j = 1 ; j <= i ; j++) {
				final double r = (1L << (2 * j)) - 1;
				final double tIJm1 = currentRow[(j - 1)];
				currentRow[j] = tIJm1 + ((tIJm1 - (previousRow[(j - 1)])) / r);
			}
			final double s = currentRow[i];
			if (i >= (getMinimalIterationCount())) {
				final double delta = org.apache.commons.math3.util.FastMath.abs((s - olds));
				final double rLimit = ((getRelativeAccuracy()) * ((org.apache.commons.math3.util.FastMath.abs(olds)) + (org.apache.commons.math3.util.FastMath.abs(s)))) * 0.5;
				if ((delta <= rLimit) || (delta <= (getAbsoluteAccuracy()))) {
					return s;
				} 
			} 
			olds = s;
		}
	}
}

