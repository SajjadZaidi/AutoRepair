package org.apache.commons.math3.analysis.integration.gauss;


public class GaussIntegrator {
	private final double[] points;

	private final double[] weights;

	public GaussIntegrator(double[] points ,double[] weights) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NonMonotonicSequenceException {
		if ((points.length) != (weights.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(points.length , weights.length);
		} 
		org.apache.commons.math3.util.MathArrays.checkOrder(points, org.apache.commons.math3.util.MathArrays.OrderDirection.INCREASING, true, true);
		this.points = points.clone();
		this.weights = weights.clone();
	}

	public GaussIntegrator(org.apache.commons.math3.util.Pair<double[], double[]> pointsAndWeights) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
		this(pointsAndWeights.getFirst(), pointsAndWeights.getSecond());
	}

	public double integrate(org.apache.commons.math3.analysis.UnivariateFunction f) {
		double s = 0;
		double c = 0;
		for (int i = 0 ; i < (points.length) ; i++) {
			final double x = points[i];
			final double w = weights[i];
			final double y = (w * (f.value(x))) - c;
			final double t = s + y;
			c = (t - s) - y;
			s = t;
		}
		return s;
	}

	public int getNumberOfPoints() {
		return points.length;
	}

	public double getPoint(int index) {
		return points[index];
	}

	public double getWeight(int index) {
		return weights[index];
	}
}

