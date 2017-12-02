package org.apache.commons.math3.distribution;


public class ParetoDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;

	private static final long serialVersionUID = 20130424;

	private final double scale;

	private final double shape;

	private final double solverAbsoluteAccuracy;

	public ParetoDistribution() {
		this(1, 1);
	}

	public ParetoDistribution(double scale ,double shape) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		this(scale, shape, org.apache.commons.math3.distribution.ParetoDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public ParetoDistribution(double scale ,double shape ,double inverseCumAccuracy) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		this(new org.apache.commons.math3.random.Well19937c(), scale, shape, inverseCumAccuracy);
	}

	public ParetoDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double scale ,double shape) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		this(rng, scale, shape, org.apache.commons.math3.distribution.ParetoDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public ParetoDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double scale ,double shape ,double inverseCumAccuracy) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		super(rng);
		if (scale <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.SCALE , scale);
		} 
		if (shape <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.SHAPE , shape);
		} 
		this.scale = scale;
		this.shape = shape;
		this.solverAbsoluteAccuracy = inverseCumAccuracy;
	}

	public double getScale() {
		return scale;
	}

	public double getShape() {
		return shape;
	}

	public double density(double x) {
		if (x < (scale)) {
			return 0;
		} 
		return ((org.apache.commons.math3.util.FastMath.pow(scale, shape)) / (org.apache.commons.math3.util.FastMath.pow(x, ((shape) + 1)))) * (shape);
	}

	@java.lang.Override
	public double logDensity(double x) {
		if (x < (scale)) {
			return java.lang.Double.NEGATIVE_INFINITY;
		} 
		return (((org.apache.commons.math3.util.FastMath.log(scale)) * (shape)) - ((org.apache.commons.math3.util.FastMath.log(x)) * ((shape) + 1))) + (org.apache.commons.math3.util.FastMath.log(shape));
	}

	public double cumulativeProbability(double x) {
		if (x <= (scale)) {
			return 0;
		} 
		return 1 - (org.apache.commons.math3.util.FastMath.pow(((scale) / x), shape));
	}

	@java.lang.Override
	@java.lang.Deprecated
	public double cumulativeProbability(double x0, double x1) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		return probability(x0, x1);
	}

	@java.lang.Override
	protected double getSolverAbsoluteAccuracy() {
		return solverAbsoluteAccuracy;
	}

	public double getNumericalMean() {
		if ((shape) <= 1) {
			return java.lang.Double.POSITIVE_INFINITY;
		} 
		return ((shape) * (scale)) / ((shape) - 1);
	}

	public double getNumericalVariance() {
		if ((shape) <= 2) {
			return java.lang.Double.POSITIVE_INFINITY;
		} 
		double s = (shape) - 1;
		return ((((scale) * (scale)) * (shape)) / (s * s)) / ((shape) - 2);
	}

	public double getSupportLowerBound() {
		return scale;
	}

	public double getSupportUpperBound() {
		return java.lang.Double.POSITIVE_INFINITY;
	}

	public boolean isSupportLowerBoundInclusive() {
		return true;
	}

	public boolean isSupportUpperBoundInclusive() {
		return false;
	}

	public boolean isSupportConnected() {
		return true;
	}

	@java.lang.Override
	public double sample() {
		final double n = random.nextDouble();
		return (scale) / (org.apache.commons.math3.util.FastMath.pow(n, (1 / (shape))));
	}
}

