package org.apache.commons.math3.distribution;


public class LogisticDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	private static final long serialVersionUID = 20141003;

	private final double mu;

	private final double s;

	public LogisticDistribution(double mu ,double s) {
		this(new org.apache.commons.math3.random.Well19937c(), mu, s);
	}

	public LogisticDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double mu ,double s) {
		super(rng);
		if (s <= 0.0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NOT_POSITIVE_SCALE , s);
		} 
		this.mu = mu;
		this.s = s;
	}

	public double getLocation() {
		return mu;
	}

	public double getScale() {
		return s;
	}

	public double density(double x) {
		double z = (x - (mu)) / (s);
		double v = org.apache.commons.math3.util.FastMath.exp((-z));
		return ((1 / (s)) * v) / ((1.0 + v) * (1.0 + v));
	}

	public double cumulativeProbability(double x) {
		double z = (1 / (s)) * (x - (mu));
		return 1.0 / (1.0 + (org.apache.commons.math3.util.FastMath.exp((-z))));
	}

	@java.lang.Override
	public double inverseCumulativeProbability(double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0.0 , 1.0);
		} else if (p == 0) {
			return 0.0;
		} else if (p == 1) {
			return java.lang.Double.POSITIVE_INFINITY;
		} 
		return ((s) * (java.lang.Math.log((p / (1.0 - p))))) + (mu);
	}

	public double getNumericalMean() {
		return mu;
	}

	public double getNumericalVariance() {
		return ((org.apache.commons.math3.util.MathUtils.PI_SQUARED) / 3.0) * (1.0 / ((s) * (s)));
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

