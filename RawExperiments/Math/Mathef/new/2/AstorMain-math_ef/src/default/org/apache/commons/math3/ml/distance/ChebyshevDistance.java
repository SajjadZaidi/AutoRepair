package org.apache.commons.math3.ml.distance;


public class ChebyshevDistance implements org.apache.commons.math3.ml.distance.DistanceMeasure {
	private static final long serialVersionUID = -4694868171115238296L;

	public double compute(double[] a, double[] b) {
		return org.apache.commons.math3.util.MathArrays.distanceInf(a, b);
	}
}

