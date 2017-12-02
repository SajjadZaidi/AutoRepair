package org.apache.commons.math3.distribution;


public class GumbelDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	private static final long serialVersionUID = 20141003;

	private static final double EULER = (org.apache.commons.math3.util.FastMath.PI) / (2 * (org.apache.commons.math3.util.FastMath.E));

	private final double mu;

	private final double beta;

	public GumbelDistribution(double mu ,double beta) {
		this(new org.apache.commons.math3.random.Well19937c(), mu, beta);
	}

	public GumbelDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double mu ,double beta) {
		super(rng);
		if (beta <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.SCALE , beta);
		} 
		this.beta = beta;
		this.mu = mu;
	}

	public double getLocation() {
		return mu;
	}

	public double getScale() {
		return beta;
	}

	public double density(double x) {
		final double z = (x - (mu)) / (beta);
		final double t = org.apache.commons.math3.util.FastMath.exp((-z));
		return (org.apache.commons.math3.util.FastMath.exp(((-z) - t))) / (beta);
	}

	public double cumulativeProbability(double x) {
		final double z = (x - (mu)) / (beta);
		return org.apache.commons.math3.util.FastMath.exp((-(org.apache.commons.math3.util.FastMath.exp((-z)))));
	}

	@java.lang.Override
	public double inverseCumulativeProbability(double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0.0 , 1.0);
		} else if (p == 0) {
			return java.lang.Double.NEGATIVE_INFINITY;
		} else if (p == 1) {
			return java.lang.Double.POSITIVE_INFINITY;
		} 
		return (mu) - ((org.apache.commons.math3.util.FastMath.log((-(org.apache.commons.math3.util.FastMath.log(p))))) * (beta));
	}

	public double getNumericalMean() {
		return (mu) + ((org.apache.commons.math3.distribution.GumbelDistribution.EULER) * (beta));
	}

	public double getNumericalVariance() {
		return ((org.apache.commons.math3.util.MathUtils.PI_SQUARED) / 6.0) * ((beta) * (beta));
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
}

