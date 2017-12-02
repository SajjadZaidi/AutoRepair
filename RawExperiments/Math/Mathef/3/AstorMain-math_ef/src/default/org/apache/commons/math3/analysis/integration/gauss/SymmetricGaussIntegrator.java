package org.apache.commons.math3.analysis.integration.gauss;


public class SymmetricGaussIntegrator extends org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator {
	public SymmetricGaussIntegrator(double[] points ,double[] weights) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NonMonotonicSequenceException {
		super(points, weights);
	}

	public SymmetricGaussIntegrator(org.apache.commons.math3.util.Pair<double[], double[]> pointsAndWeights) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
		this(pointsAndWeights.getFirst(), pointsAndWeights.getSecond());
	}

	@java.lang.Override
	public double integrate(org.apache.commons.math3.analysis.UnivariateFunction f) {
		final int ruleLength = getNumberOfPoints();
		if (ruleLength == 1) {
			return (getWeight(0)) * (f.value(0.0));
		} 
		final int iMax = ruleLength / 2;
		double s = 0;
		double c = 0;
		for (int i = 0 ; i < iMax ; i++) {
			final double p = getPoint(i);
			final double w = getWeight(i);
			final double f1 = f.value(p);
			final double f2 = f.value((-p));
			final double y = (w * (f1 + f2)) - c;
			final double t = s + y;
			c = (t - s) - y;
			s = t;
		}
		if ((ruleLength % 2) != 0) {
			final double w = getWeight(iMax);
			final double y = (w * (f.value(0.0))) - c;
			final double t = s + y;
			s = t;
		} 
		return s;
	}
}

