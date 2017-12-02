package org.apache.commons.math3.distribution;


public class LaplaceDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	private static final long serialVersionUID = 20141003;

	private final double mu;

	private final double beta;

	public LaplaceDistribution(double mu ,double beta) {
		this(new org.apache.commons.math3.random.Well19937c(), mu, beta);
	}

	public LaplaceDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double mu ,double beta) {
		super(rng);
		if (beta <= 0.0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NOT_POSITIVE_SCALE , beta);
		} 
		this.mu = mu;
		this.beta = beta;
	}

	public double getLocation() {
		return mu;
	}

	public double getScale() {
		return beta;
	}

	public double density(double x) {
		return (org.apache.commons.math3.util.FastMath.exp(((-(org.apache.commons.math3.util.FastMath.abs((x - (mu))))) / (beta)))) / (2.0 * (beta));
	}

	public double cumulativeProbability(double x) {
		if (x <= (mu)) {
			return (org.apache.commons.math3.util.FastMath.exp(((x - (mu)) / (beta)))) / 2.0;
		} else {
			return 1.0 - ((org.apache.commons.math3.util.FastMath.exp((((mu) - x) / (beta)))) / 2.0);
		}
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
		double x = p > 0.5 ? -(java.lang.Math.log((2.0 - (2.0 * p)))) : java.lang.Math.log((2.0 * p));
		return (mu) + ((beta) * x);
	}

	public double getNumericalMean() {
		return mu;
	}

	public double getNumericalVariance() {
		return (2.0 * (beta)) * (beta);
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

