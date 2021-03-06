package org.apache.commons.math.distribution;


public class WeibullDistributionImpl extends org.apache.commons.math.distribution.AbstractContinuousDistribution implements java.io.Serializable , org.apache.commons.math.distribution.WeibullDistribution {
	private static final long serialVersionUID = 8589540077390120676L;

	private double alpha;

	private double beta;

	public WeibullDistributionImpl(double alpha ,double beta) {
		super();
		setShape(alpha);
		setScale(beta);
	}

	public double cumulativeProbability(double x) {
		double ret;
		if (x <= 0.0) {
			ret = 0.0;
		} else {
			ret = 1.0 - (java.lang.Math.exp((-(java.lang.Math.pow((x / (getScale())), getShape())))));
		}
		return ret;
	}

	public double getShape() {
		return alpha;
	}

	public double getScale() {
		return beta;
	}

	public double inverseCumulativeProbability(double p) {
		double ret;
		if ((p < 0.0) || (p > 1.0)) {
			throw new java.lang.IllegalArgumentException("probability argument must be between 0 and 1 (inclusive)");
		} else if (p == 0) {
			ret = 0.0;
		} else if (p == 1) {
			ret = java.lang.Double.POSITIVE_INFINITY;
		} else {
			ret = (getScale()) * (java.lang.Math.pow((-(java.lang.Math.log((1.0 - p)))), (1.0 / (getShape()))));
		}
		return ret;
	}

	public void setShape(double alpha) {
		if (alpha <= 0.0) {
			throw new java.lang.IllegalArgumentException("Shape must be positive.");
		} 
		org.apache.commons.math.distribution.WeibullDistributionImpl.this.alpha = alpha;
	}

	public void setScale(double beta) {
		if (beta <= 0.0) {
			throw new java.lang.IllegalArgumentException("Scale must be positive.");
		} 
		org.apache.commons.math.distribution.WeibullDistributionImpl.this.beta = beta;
	}

	protected double getDomainLowerBound(double p) {
		return 0.0;
	}

	protected double getDomainUpperBound(double p) {
		return java.lang.Double.MAX_VALUE;
	}

	protected double getInitialDomain(double p) {
		return java.lang.Math.pow(((getScale()) * (java.lang.Math.log(2.0))), (1.0 / (getShape())));
	}
}

