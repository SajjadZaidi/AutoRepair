package org.apache.commons.math3.distribution;


public class LevyDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	private static final long serialVersionUID = 20130314L;

	private final double mu;

	private final double c;

	private final double halfC;

	public LevyDistribution(final double mu ,final double c) {
		this(new org.apache.commons.math3.random.Well19937c(), mu, c);
	}

	public LevyDistribution(final org.apache.commons.math3.random.RandomGenerator rng ,final double mu ,final double c) {
		super(rng);
		this.mu = mu;
		this.c = c;
		this.halfC = 0.5 * c;
	}

	public double density(final double x) {
		if (x < (mu)) {
			return java.lang.Double.NaN;
		} 
		final double delta = x - (mu);
		final double f = (halfC) / delta;
		return ((org.apache.commons.math3.util.FastMath.sqrt((f / (org.apache.commons.math3.util.FastMath.PI)))) * (org.apache.commons.math3.util.FastMath.exp((-f)))) / delta;
	}

	@java.lang.Override
	public double logDensity(double x) {
		if (x < (mu)) {
			return java.lang.Double.NaN;
		} 
		final double delta = x - (mu);
		final double f = (halfC) / delta;
		return ((0.5 * (org.apache.commons.math3.util.FastMath.log((f / (org.apache.commons.math3.util.FastMath.PI))))) - f) - (org.apache.commons.math3.util.FastMath.log(delta));
	}

	public double cumulativeProbability(final double x) {
		if (x < (mu)) {
			return java.lang.Double.NaN;
		} 
		return org.apache.commons.math3.special.Erf.erfc(org.apache.commons.math3.util.FastMath.sqrt(((halfC) / (x - (mu)))));
	}

	@java.lang.Override
	public double inverseCumulativeProbability(final double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		final double t = org.apache.commons.math3.special.Erf.erfcInv(p);
		return (mu) + ((halfC) / (t * t));
	}

	public double getScale() {
		return c;
	}

	public double getLocation() {
		return mu;
	}

	public double getNumericalMean() {
		return java.lang.Double.POSITIVE_INFINITY;
	}

	public double getNumericalVariance() {
		return java.lang.Double.POSITIVE_INFINITY;
	}

	public double getSupportLowerBound() {
		return mu;
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
}

