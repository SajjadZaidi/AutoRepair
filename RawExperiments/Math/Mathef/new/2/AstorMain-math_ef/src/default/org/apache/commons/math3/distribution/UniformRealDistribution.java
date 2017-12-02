package org.apache.commons.math3.distribution;


public class UniformRealDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	@java.lang.Deprecated
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;

	private static final long serialVersionUID = 20120109L;

	private final double lower;

	private final double upper;

	public UniformRealDistribution() {
		this(0, 1);
	}

	public UniformRealDistribution(double lower ,double upper) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		this(new org.apache.commons.math3.random.Well19937c(), lower, upper);
	}

	@java.lang.Deprecated
	public UniformRealDistribution(double lower ,double upper ,double inverseCumAccuracy) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		this(new org.apache.commons.math3.random.Well19937c(), lower, upper);
	}

	@java.lang.Deprecated
	public UniformRealDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double lower ,double upper ,double inverseCumAccuracy) {
		this(rng, lower, upper);
	}

	public UniformRealDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double lower ,double upper) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		super(rng);
		if (lower >= upper) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND , lower , upper , false);
		} 
		this.lower = lower;
		this.upper = upper;
	}

	public double density(double x) {
		if ((x < (lower)) || (x > (upper))) {
			return 0.0;
		} 
		return 1 / ((upper) - (lower));
	}

	public double cumulativeProbability(double x) {
		if (x <= (lower)) {
			return 0;
		} 
		if (x >= (upper)) {
			return 1;
		} 
		return (x - (lower)) / ((upper) - (lower));
	}

	@java.lang.Override
	public double inverseCumulativeProbability(final double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		return (p * ((upper) - (lower))) + (lower);
	}

	public double getNumericalMean() {
		return 0.5 * ((lower) + (upper));
	}

	public double getNumericalVariance() {
		double ul = (upper) - (lower);
		return (ul * ul) / 12;
	}

	public double getSupportLowerBound() {
		return lower;
	}

	public double getSupportUpperBound() {
		return upper;
	}

	public boolean isSupportLowerBoundInclusive() {
		return true;
	}

	public boolean isSupportUpperBoundInclusive() {
		return true;
	}

	public boolean isSupportConnected() {
		return true;
	}

	@java.lang.Override
	public double sample() {
		final double u = random.nextDouble();
		return (u * (upper)) + ((1 - u) * (lower));
	}
}

