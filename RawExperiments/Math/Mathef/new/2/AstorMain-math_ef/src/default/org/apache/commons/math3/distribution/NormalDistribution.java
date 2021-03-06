package org.apache.commons.math3.distribution;


public class NormalDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;

	private static final long serialVersionUID = 8589540077390120676L;

	private static final double SQRT2 = org.apache.commons.math3.util.FastMath.sqrt(2.0);

	private final double mean;

	private final double standardDeviation;

	private final double logStandardDeviationPlusHalfLog2Pi;

	private final double solverAbsoluteAccuracy;

	public NormalDistribution() {
		this(0, 1);
	}

	public NormalDistribution(double mean ,double sd) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		this(mean, sd, org.apache.commons.math3.distribution.NormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public NormalDistribution(double mean ,double sd ,double inverseCumAccuracy) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		this(new org.apache.commons.math3.random.Well19937c(), mean, sd, inverseCumAccuracy);
	}

	public NormalDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double mean ,double sd) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		this(rng, mean, sd, org.apache.commons.math3.distribution.NormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public NormalDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double mean ,double sd ,double inverseCumAccuracy) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		super(rng);
		if (sd <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.STANDARD_DEVIATION , sd);
		} 
		this.mean = mean;
		standardDeviation = sd;
		logStandardDeviationPlusHalfLog2Pi = (org.apache.commons.math3.util.FastMath.log(sd)) + (0.5 * (org.apache.commons.math3.util.FastMath.log((2 * (org.apache.commons.math3.util.FastMath.PI)))));
		solverAbsoluteAccuracy = inverseCumAccuracy;
	}

	public double getMean() {
		return mean;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public double density(double x) {
		return org.apache.commons.math3.util.FastMath.exp(logDensity(x));
	}

	@java.lang.Override
	public double logDensity(double x) {
		final double x0 = x - (mean);
		final double x1 = x0 / (standardDeviation);
		return (((-0.5) * x1) * x1) - (logStandardDeviationPlusHalfLog2Pi);
	}

	public double cumulativeProbability(double x) {
		final double dev = x - (mean);
		if ((org.apache.commons.math3.util.FastMath.abs(dev)) > (40 * (standardDeviation))) {
			return dev < 0 ? 0.0 : 1.0;
		} 
		return 0.5 * (1 + (org.apache.commons.math3.special.Erf.erf((dev / ((standardDeviation) * (org.apache.commons.math3.distribution.NormalDistribution.SQRT2))))));
	}

	@java.lang.Override
	public double inverseCumulativeProbability(final double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		return (mean) + (((standardDeviation) * (org.apache.commons.math3.distribution.NormalDistribution.SQRT2)) * (org.apache.commons.math3.special.Erf.erfInv(((2 * p) - 1))));
	}

	@java.lang.Override
	@java.lang.Deprecated
	public double cumulativeProbability(double x0, double x1) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		return probability(x0, x1);
	}

	@java.lang.Override
	public double probability(double x0, double x1) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		if (x0 > x1) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT , x0 , x1 , true);
		} 
		final double denom = (standardDeviation) * (org.apache.commons.math3.distribution.NormalDistribution.SQRT2);
		final double v0 = (x0 - (mean)) / denom;
		final double v1 = (x1 - (mean)) / denom;
		return 0.5 * (org.apache.commons.math3.special.Erf.erf(v0, v1));
	}

	@java.lang.Override
	protected double getSolverAbsoluteAccuracy() {
		return solverAbsoluteAccuracy;
	}

	public double getNumericalMean() {
		return getMean();
	}

	public double getNumericalVariance() {
		final double s = getStandardDeviation();
		return s * s;
	}

	public double getSupportLowerBound() {
		return java.lang.Double.NEGATIVE_INFINITY;
	}

	public double getSupportUpperBound() {
		return java.lang.Double.POSITIVE_INFINITY;
	}

	public boolean isSupportLowerBoundInclusive() {
		return false;
	}

	public boolean isSupportUpperBoundInclusive() {
		return false;
	}

	public boolean isSupportConnected() {
		return true;
	}

	@java.lang.Override
	public double sample() {
		return ((standardDeviation) * (random.nextGaussian())) + (mean);
	}
}

