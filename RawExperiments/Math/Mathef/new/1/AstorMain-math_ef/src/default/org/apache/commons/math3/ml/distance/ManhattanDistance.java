package org.apache.commons.math3.ml.distance;


public class ManhattanDistance implements org.apache.commons.math3.ml.distance.DistanceMeasure {
	private static final long serialVersionUID = -9108154600539125566L;

	public double compute(double[] a, double[] b) {
		return org.apache.commons.math3.util.MathArrays.distance1(a, b);
	}
}

