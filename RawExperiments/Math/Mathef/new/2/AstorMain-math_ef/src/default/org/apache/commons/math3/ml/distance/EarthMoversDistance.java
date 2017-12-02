package org.apache.commons.math3.ml.distance;


public class EarthMoversDistance implements org.apache.commons.math3.ml.distance.DistanceMeasure {
	private static final long serialVersionUID = -5406732779747414922L;

	public double compute(double[] a, double[] b) {
		double lastDistance = 0;
		double totalDistance = 0;
		for (int i = 0 ; i < (a.length) ; i++) {
			final double currentDistance = ((a[i]) + lastDistance) - (b[i]);
			totalDistance += org.apache.commons.math3.util.FastMath.abs(currentDistance);
			lastDistance = currentDistance;
		}
		return totalDistance;
	}
}

