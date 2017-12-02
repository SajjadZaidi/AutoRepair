package org.apache.commons.math3.ml.distance;


public class EuclideanDistance implements org.apache.commons.math3.ml.distance.DistanceMeasure {
	private static final long serialVersionUID = 1717556319784040040L;

	public double compute(double[] a, double[] b) {
		return org.apache.commons.math3.util.MathArrays.distance(a, b);
	}
}

