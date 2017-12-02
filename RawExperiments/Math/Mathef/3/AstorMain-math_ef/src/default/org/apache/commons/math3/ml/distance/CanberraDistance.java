package org.apache.commons.math3.ml.distance;


public class CanberraDistance implements org.apache.commons.math3.ml.distance.DistanceMeasure {
	private static final long serialVersionUID = -6972277381587032228L;

	public double compute(double[] a, double[] b) {
		double sum = 0;
		for (int i = 0 ; i < (a.length) ; i++) {
			final double num = org.apache.commons.math3.util.FastMath.abs(((a[i]) - (b[i])));
			final double denom = (org.apache.commons.math3.util.FastMath.abs(a[i])) + (org.apache.commons.math3.util.FastMath.abs(b[i]));
			sum += (num == 0.0) && (denom == 0.0) ? 0.0 : num / denom;
		}
		return sum;
	}
}

