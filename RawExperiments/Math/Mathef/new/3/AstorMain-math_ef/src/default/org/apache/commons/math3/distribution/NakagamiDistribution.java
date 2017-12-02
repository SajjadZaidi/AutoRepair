package org.apache.commons.math3.distribution;


public class NakagamiDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;

	private static final long serialVersionUID = 20141003;

	private final double mu;

	private final double omega;

	private final double inverseAbsoluteAccuracy;

	public NakagamiDistribution(double mu ,double omega) {
		this(mu, omega, org.apache.commons.math3.distribution.NakagamiDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
	}

	public NakagamiDistribution(double mu ,double omega ,double inverseAbsoluteAccuracy) {
		this(new org.apache.commons.math3.random.Well19937c(), mu, omega, inverseAbsoluteAccuracy);
	}

	public NakagamiDistribution(org.apache.commons.math3.random.RandomGenerator rng ,double mu ,double omega ,double inverseAbsoluteAccuracy) {
		super(rng);
		if (mu < 0.5) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(mu , 0.5 , true);
		} 
		if (omega <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(org.apache.commons.math3.exception.util.LocalizedFormats.NOT_POSITIVE_SCALE , omega);
		} 
		this.mu = mu;
		this.omega = omega;
		this.inverseAbsoluteAccuracy = inverseAbsoluteAccuracy;
	}

	public double getShape() {
		return mu;
	}

	public double getScale() {
		return omega;
	}

	@java.lang.Override
	protected double getSolverAbsoluteAccuracy() {
		return inverseAbsoluteAccuracy;
	}

	public double density(double x) {
		if (x <= 0) {
			return 0.0;
		} 
		return (((2.0 * (org.apache.commons.math3.util.FastMath.pow(mu, mu))) / ((org.apache.commons.math3.special.Gamma.gamma(mu)) * (org.apache.commons.math3.util.FastMath.pow(omega, mu)))) * (org.apache.commons.math3.util.FastMath.pow(x, ((2 * (mu)) - 1)))) * (org.apache.commons.math3.util.FastMath.exp(((((-(mu)) * x) * x) / (omega))));
	}

	public double cumulativeProbability(double x) {
		return org.apache.commons.math3.special.Gamma.regularizedGammaP(mu, ((((mu) * x) * x) / (omega)));
	}

	public double getNumericalMean() {
		return ((org.apache.commons.math3.special.Gamma.gamma(((mu) + 0.5))) / (org.apache.commons.math3.special.Gamma.gamma(mu))) * (org.apache.commons.math3.util.FastMath.sqrt(((omega) / (mu))));
	}

	public double getNumericalVariance() {
		double v = (org.apache.commons.math3.special.Gamma.gamma(((mu) + 0.5))) / (org.apache.commons.math3.special.Gamma.gamma(mu));
		return (omega) * (1 - (((1 / (mu)) * v) * v));
	}

	public double getSupportLowerBound() {
		return 0;
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
}

