package org.apache.commons.math3.distribution;


public class ConstantRealDistribution extends org.apache.commons.math3.distribution.AbstractRealDistribution {
	private static final long serialVersionUID = -4157745166772046273L;

	private final double value;

	public ConstantRealDistribution(double value) {
		super(null);
		this.value = value;
	}

	public double density(double x) {
		return x == (value) ? 1 : 0;
	}

	public double cumulativeProbability(double x) {
		return x < (value) ? 0 : 1;
	}

	@java.lang.Override
	public double inverseCumulativeProbability(final double p) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((p < 0.0) || (p > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(p , 0 , 1);
		} 
		return value;
	}

	public double getNumericalMean() {
		return value;
	}

	public double getNumericalVariance() {
		return 0;
	}

	public double getSupportLowerBound() {
		return value;
	}

	public double getSupportUpperBound() {
		return value;
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
		return value;
	}

	@java.lang.Override
	public void reseedRandomGenerator(long seed) {
	}
}

